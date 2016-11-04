package services.interfaces;


import models.store.ChatSession;
import java.util.UUID;


public interface ChatSessionService extends DataService<ChatSession> {
    public ChatSession  findWithMessage(UUID chatSessionId);
}
