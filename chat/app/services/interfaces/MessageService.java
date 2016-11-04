package services.interfaces;


import exceptions.RecordNotFoundException;
import models.domain.UserMessage;
import models.store.Message;

import java.util.List;
import java.util.UUID;


public interface MessageService extends DataService<Message> {
    public List<UserMessage> findUserMessages(UUID chatSessionId, UUID userId);
    public Boolean markMessageAsSeenFor(UUID messageId, UUID userId) throws RecordNotFoundException;
}
