package chat;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class UsersBD {
    
    private static final String dbUrl = "jdbc:h2:tcp://localhost/~/test7";

    /**
     * Конструктор класса, регистрирует драйвер БД.
     */
    public UsersBD() {
        try {
            Class.forName("org.h2.Driver");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(UsersBD.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /**
     * Вспомогательный метод для закрытия соединений с БД.
     * @param closeable 
     */
    private static void closeQuietly(Connection closeable) {
        if (closeable != null) {
            try {
                closeable.close();
            } catch (SQLException ex) {
                // ignore
            }
        }
    }
    
    /**
     * Создать БД.
     * @return в случае успеха возвражает пустой JSON объект, иначе null.
     */
    public JSONObject createDB() {
        Connection conn = null;
        JSONObject result = null;
        try {
            conn = DriverManager.getConnection(dbUrl);
            Statement st = conn.createStatement();
            st.execute("create table users(id INT PRIMARY KEY AUTO_INCREMENT, login varchar(255),"
                    + "pass varchar(255), status varchar(255))");
            
            result = new JSONObject();
        } catch (SQLException ex) {
            Logger.getLogger(UsersBD.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            closeQuietly(conn);
        }
        return result;
    }

    /**
     * 
     * @param username логин
     * @param pass пароль
     * @return есть или нет пользователь
     */
    public boolean checking1(String username, String pass) {
        boolean check = false;
        Connection conn = null;
        JSONArray list = null;
        try {
            conn = DriverManager.getConnection(dbUrl);
            
            String q = "SELECT login, pass, status FROM USERS WHERE login = ?";
            
            PreparedStatement st = conn.prepareStatement(q);
            st.setString(1, username);
            ResultSet rs = st.executeQuery();
            
            System.out.println(rs);
            if(rs.next()){
                if(rs.getString("PASS").equals(pass)){
                    check = true;
                }
            }
        }
        catch (Exception e){
            System.out.println("Database exception.");
            return false;
        }
        finally {
            closeQuietly(conn);
        }
        return check;
    }
    
    /**
     * 
     * @param username ник
     * @return есть или нет пользователь
     */
    public boolean checking(String username) {
        boolean check = false;
        Connection conn = null;
        JSONArray list = null;
        try {
            conn = DriverManager.getConnection(dbUrl);
            
            String q = "SELECT login FROM USERS WHERE login = ?";
            
            PreparedStatement st = conn.prepareStatement(q);
            st.setString(1, username);
            ResultSet rs = st.executeQuery();
            
            System.out.println(rs);
            if(rs.next()){
                check = true;
            }
        }
        catch (Exception e){
            System.out.println("Database exception.");
            return false;
        }
        finally {
            closeQuietly(conn);
        }
        return check;
    }
    
    public void setStatus(String username, String stts) {
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(dbUrl);
            String q = "UPDATE users SET (status) = (?) WHERE login = ?";
            PreparedStatement st = conn.prepareStatement(q);
            st.setString(1, stts);
            st.setString(2, username);
            st.execute();
        }
        catch (Exception e){
            System.out.println("Database exception.");
        }
        finally {
            closeQuietly(conn);
        }
    }
    
    /**
     * 
     * @return список пользователей
     */
    public JSONArray whoOn() {
        Connection conn = null;
        JSONArray list = null;
        try {
            conn = DriverManager.getConnection(dbUrl);
            String q = "SELECT * FROM users WHERE status = ?";
            PreparedStatement st = conn.prepareStatement(q);
            st.setString(1,"on");
            ResultSet rs = st.executeQuery();
         
            list = new JSONArray();
            while (rs.next()) {
                Users c = new Users(rs.getLong("ID"),
                        rs.getString("LOGIN"),
                        rs.getString("PASS"),
                        rs.getString("STATUS"));
                list.add(c);
            }
            System.out.println(list);
        } catch (SQLException ex) {
            Logger.getLogger(MessBD.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            closeQuietly(conn);
        }
        return list;
    }

   
    /**
     * Добавить пользователя.
     * @param c пользователь, в качестве id любое значение или null
     * @return в случае успеха возвражает id контакта в формате JSON, иначе null.
     */
    public JSONObject add(Users c) {
        Connection conn = null;
        JSONObject result = null;
        try {
            if(!checking(c.getLOGIN())){
                conn = DriverManager.getConnection(dbUrl);
            
                String q = "INSERT INTO USERS(login,pass,status) VALUES(?,?,?)";
                PreparedStatement st = conn.prepareStatement(q);

                st.setString(1, c.getLOGIN());
                st.setString(2, c.getPASS());
                st.setString(3, c.getSTTS());
                st.execute();

                long id = -1;
                ResultSet rs = st.getGeneratedKeys();
                if (rs.next()) {
                    id = rs.getInt(1);
                }

                result = new JSONObject();
                result.put("id", id);
            }
            else {
                System.out.println("est uje");
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(UsersBD.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            closeQuietly(conn);
        }
        return result;
    } 
}
