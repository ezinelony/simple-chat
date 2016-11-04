package repository.interfaces;


import exceptions.RecordNotFoundException;
import repository.RelationalStore;

import java.util.List;
import java.util.UUID;

public abstract class Message extends RelationalStore<models.store.Message> {
    
    abstract public List<models.store.Message> findBySession(UUID chatSessionId);
    abstract public List<models.store.Message> findByAuthor(UUID authorId);
    abstract public List<models.store.Message> findSeenBy(UUID chatSessionId, UUID userId);
    abstract public List<models.store.Message> findNotSeenBy(UUID chatSessionId, UUID userId);
    abstract public void addUserToSeen(UUID messageId, UUID userId) throws RecordNotFoundException;
}