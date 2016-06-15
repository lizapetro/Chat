package chat;

import java.util.Date;
import org.json.simple.JSONAware;
import org.json.simple.JSONObject;

public class Message implements JSONAware {

    private Long id;
    private String nick;
    private String mess;
    private Long dt;

    /**
     * Конструктор сообщения.
     * @param id идентификатор 
     * @param nick username 
     * @param mess текст
     * @param dt дата
     */
    
    public Message(Long id, String nick, String mess, Long dt) {
        this.id = id;
        this.nick = nick;
        this.mess = mess;
        this.dt = dt;
    }
    
    /**
     * 
     * @return айди
     */
    public Long getId() {
        return id;
    }
    
    /**
     * 
     * @return никнейм
     */
    public String getNICK() {
        return nick;
    }
    
    /**
     * 
     * @return текст
     */
    public String getMESS(){
        return mess;
    }
    
    /**
     * 
     * @return дата
     */
    public Long getDT(){
        return dt;
    }

    /**
     * Преобразует данные контакта в JSON строку.
     * @return данные сообщения в формате JSON.
     */
    @Override
    public String toJSONString() {
        StringBuilder sb = new StringBuilder();

        sb.append("{");

        sb.append("\"").append(JSONObject.escape("id")).append("\"");
        sb.append(":");
        sb.append(id);

        sb.append(",");
        
        sb.append("\"").append("nick").append("\"");
        sb.append(":");
        sb.append("\"").append(JSONObject.escape(nick)).append("\"");

        sb.append(",");
        
        sb.append("\"").append("mess").append("\"");
        sb.append(":");
        sb.append("\"").append(JSONObject.escape(mess)).append("\"");
        
        sb.append(",");
        
        sb.append("\"").append("dt").append("\"");
        sb.append(":");
        sb.append(dt);
        
        sb.append("}");

        return sb.toString();
    }
}