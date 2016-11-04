package repository;


import com.avaje.ebean.Ebean;
import exceptions.RecordNotFoundException;
import models.store.Message;
import models.store.User;

import java.util.List;
import java.util.UUID;


public class MessageDao extends repository.interfaces.Message {

    @Override
    public Message create(Message message) throws Exception {
         Ebean.saveManyToManyAssociations(message, "seenBy");
        return find(message.getId());
    }

    /**
     * * 
     * @return
     */

    @Override
    public Class<Message> entityClass() {
        return Message.class;
    }

    @Override
    public List<Message> findBySession(UUID chatSessionId) {
        return Ebean.find(Message.class)
                .select("chatSession.id, creator.id, creator.username")
                .where()
                .eq("chatSession.id", chatSessionId)
                .findList();
    }

    @Override
    public List<Message> findByAuthor(UUID authorId) {
        return Ebean.find(Message.class)
                .select("chatSession.id, creator.id, creator.username")
                .where()
                .eq("creator.id", authorId)
                .findList();
    }

    @Override
    public List<Message> findSeenBy(UUID chatSessionId, UUID userId) {
        
        return Ebean.find(Message.class)
                .select("chatSession.id, creator.id, creator.username")
                .where()
                .eq("chatSession.id", chatSessionId)
                .contains("seenBy.id", userId.toString())
                .findList();
    }

    @Override
    public List<Message> findNotSeenBy(UUID chatSessionId, UUID userId) {

        return Ebean.find(Message.class)
                .select("chatSession.id, creator.id, creator.username")
                .where()
                .eq("chatSession.id", chatSessionId)
                .contains("seenBy.id", userId.toString())
                .findList();
    }

    
    private Message findWithSeenUsers(UUID messageId) {
        return Ebean.find(Message.class)
                .select("seenBy.id")
                .fetch("seenBy")
                .where()
                .eq("id", messageId)
                .findUnique();
    }
    
    @Override
    public void addUserToSeen(UUID messageId, UUID userId) throws RecordNotFoundException {
        Ebean.beginTransaction();
        Message m = this.findWithSeenUsers(messageId);
        User u = new User();
        u.setId(userId);
        m.getSeenBy().add(u);
        Ebean.saveManyToManyAssociations(m, "seenBy");
        Ebean.endTransaction();
        
    }
}
