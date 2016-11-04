package services;


import exceptions.RecordNotFoundException;
import models.store.ChatSession;
import services.interfaces.ChatSessionService;

import javax.inject.Inject;
import java.util.UUID;


public class StandardChatSessionService implements ChatSessionService {

    private repository.interfaces.ChatSession sessionStore;


    @Inject
    public StandardChatSessionService(repository.interfaces.ChatSession sessionStore) {
        this.sessionStore = sessionStore;
    }
    
    @Override
    public ChatSession create(ChatSession session) throws Exception {
        return sessionStore.create(session);
    }

    @Override
    public ChatSession find(UUID id) throws RecordNotFoundException {
        return sessionStore.find(id);
    }

    @Override
    public ChatSession findWithMessage(UUID chatSessionId) {
        return sessionStore.findWithMessage(chatSessionId);
    }
}
