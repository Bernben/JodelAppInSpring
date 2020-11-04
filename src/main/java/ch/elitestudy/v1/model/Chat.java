package ch.elitestudy.v1.model;

import org.ocpsoft.prettytime.PrettyTime;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Date;

@Entity
@Table(name = "chat")
public class Chat {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "msgfrom")
    private String from;

    @Column(name = "msgto")
    private String to;

    @Column(name = "message")
    private String message;

    @Column(name = "timestamp")
    private Timestamp timestamp;

    @Column(name = "isread")
    private Boolean isRead;

    @Column(name = "allowchat")
    private Boolean allowChat;


    public Boolean getRead() {
        return isRead;
    }

    public void setRead(Boolean read) {
        isRead = read;
    }

    public Boolean getAllowChat() {
        return allowChat;
    }

    public void setAllowChat(Boolean allowChat) {
        this.allowChat = allowChat;
    }

    public Boolean getIsRead() {
        return isRead;
    }

    public void setIsRead(Boolean isread) {
        this.isRead = isread;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }


    public String getTimestamp() {
        Date postedDate = new Date();
        postedDate.setTime(timestamp.getTime());
        String prettyTimeString = new PrettyTime().format(postedDate);
        return prettyTimeString;
    }

    public void setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
    }
}
