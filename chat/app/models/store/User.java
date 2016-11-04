package models.store;

//~--- non-JDK imports --------------------------------------------------------

import javax.persistence.*;
import java.util.List;

//import play.db.ebean.Model;
//~--- JDK imports ------------------------------------------------------------

@Entity
@Table(name="users_tbl")
public  class User extends BaseModel {

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Column(
            unique = true,
            updatable = false,
            name = "username",
            nullable = false,
            length = 100
    )
    private String username;


    public List<Message> getSeenMessages() {
        return seenMessages;
    }

    public void setSeenMessage(List<Message> seenMessages) {
        this.seenMessages = seenMessages;
    }

    @ManyToMany(mappedBy = "seenBy")
    private List<Message> seenMessages;
    
    public List<ChatSession> getChatSessions() {
        return chatSessions;
    }

    public void setChatSessions(List<ChatSession> chatSessions) {
        this.chatSessions = chatSessions;
    }

    @OneToMany(mappedBy = "creator")
    private List<ChatSession> chatSessions;
}

