package repository;


import com.avaje.ebean.Ebean;
import models.store.ChatSession;

import java.util.List;
import java.util.UUID;


public class ChatSessionDao extends repository.interfaces.ChatSession {

    /**
     * * 
     * @return
     */

    @Override
    public Class<ChatSession> entityClass() {
        return ChatSession.class;
    }

    @Override
    public ChatSession findWithMessage(UUID chatSessionId) {
        return Ebean.find(ChatSession.class)
                .fetch("messages")
                .where()
                .eq("id", chatSessionId)
                .findUnique();
    }

    @Override
    public List<ChatSession> all() {
        return Ebean.find(ChatSession.class).findList();
    }
}
