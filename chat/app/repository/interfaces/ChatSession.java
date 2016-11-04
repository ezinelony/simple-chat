package repository.interfaces;


import repository.RelationalStore;

import java.util.List;
import java.util.UUID;

public abstract class ChatSession extends RelationalStore<models.store.ChatSession> {
    
    abstract public models.store.ChatSession findWithMessage(UUID chatSessionId);
    abstract public List<models.store.ChatSession> all();
}