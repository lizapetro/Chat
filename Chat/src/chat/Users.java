package chat;

import org.json.simple.JSONAware;
import org.json.simple.JSONObject;

public class Users implements JSONAware {

    private Long id;
    private String login;
    private String pass;
    private String status;

    /**
     * Конструктор пользователя.
     * @param id ай ди
     * @param login логин
     * @param pass пароль
     * @param stts  роль
     */
    public Users(Long id, String login, String pass, String stts) {
        this.id = id;
        this.login = login;
        this.pass = pass;
        this.status = stts;
    }
    
    /**
     * 
     * @return ай ди
     */
    public Long getId() {
        return id;
    }

    /**
     * 
     * @return логин
     */
    public String getLOGIN() {
        return login;
    }
    
    /**
     * 
     * @return пароль
     */
    public String getPASS(){
        return pass;
    }
    
    /**
     * 
     * @return роль
     */
    public String getSTTS(){
        return status;
    }

    /**
     * Преобразует данные пользователя в JSON строку.
     * @return данные о пользователе в формате JSON.
     */
    @Override
    public String toJSONString() {
        StringBuilder sb = new StringBuilder();

        sb.append("{");

        sb.append("\"").append(JSONObject.escape("id")).append("\"");
        sb.append(":");
        sb.append(id);

        sb.append(",");
        
        sb.append("\"").append("login").append("\"");
        sb.append(":");
        sb.append("\"").append(JSONObject.escape(login)).append("\"");
        
        sb.append("}");
        
        return sb.toString();
    }
}
