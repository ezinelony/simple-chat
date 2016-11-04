package models.store;

//~--- non-JDK imports --------------------------------------------------------

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.List;
import java.util.UUID;

//import play.db.ebean.Model;
//~--- JDK imports ------------------------------------------------------------

@Entity
@Table(name="chat_sessions_tbl")
public  class ChatSession extends BaseModel {

    @JsonIgnore
    @ManyToOne
    private User creator;

    public List<Message> getMessages() {
        return messages;
    }

    public void setMessages(List<Message> messages) {
        this.messages = messages;
    }

    @OneToMany(mappedBy = "chatSession")
    private List<Message> messages;
    
    @Transient
    private String creatorId;

    public User getCreator() {
        return creator;
    }

    public void setCreator(User creator) {
        this.creator = creator;
    }

    public UUID getCreatorId() {
        return UUID.fromString(creatorId);
    }

    public void setCreatorId(String creatorId) throws IllegalArgumentException{
        try{
            UUID.fromString(creatorId);
            this.creatorId = creatorId;
        }catch (Exception e){
            throw new IllegalArgumentException("String is not in the form of UUID");
        }
    }
}

