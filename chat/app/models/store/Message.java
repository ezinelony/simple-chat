package models.store;

//~--- non-JDK imports --------------------------------------------------------

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.List;
import java.util.UUID;

//import play.db.ebean.Model;
//~--- JDK imports ------------------------------------------------------------

@Entity
@Table(name="messages_tbl")
public  class Message extends BaseModel {

 
    @Column(
            name = "body",
            nullable = false,
            length = 500
    )
    private String body;


    public List<User> getSeenBy() {
        return seenBy;
    }

    public void setSeenBy(List<User> seenBy) {
        this.seenBy = seenBy;
    }

    //@ManyToMany(mappedBy = "seenMessages")
    private List<User> seenBy;
    
    @JsonIgnore
    @ManyToOne
    private User creator;

    @JsonIgnore
    @ManyToOne
    private ChatSession chatSession;

    public UUID getCreatorId() {
        return UUID.fromString(creatorId);
    }

 

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public User getCreator() {
        return creator;
    }

    public void setCreator(User creator) {
        this.creator = creator;
    }

    public ChatSession getChatSession() {
        return chatSession;
    }

    public void setChatSession(ChatSession chatSession) {
        this.chatSession = chatSession;
    }

    public UUID getChatSessionId() {
        return UUID.fromString(chatSessionId);
    }

    public void setCreatorId(String creatorId) throws IllegalArgumentException{
        validatedStringIsUUID(creatorId);
        this.creatorId = creatorId;
    }

    public void setChatSessionId(String chatSessionId) throws IllegalArgumentException{
        validatedStringIsUUID(chatSessionId);
        this.chatSessionId = chatSessionId;
    }

    private void validatedStringIsUUID(String id) throws IllegalArgumentException{
        try {
            UUID.fromString(id);
        } catch (Exception e){
            throw new IllegalArgumentException("String is not in the form of UUID");
        }
    }
    
    @Transient
    private String creatorId;

    @Transient
    private String chatSessionId;
}

