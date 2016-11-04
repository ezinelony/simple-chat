package services;


import exceptions.RecordNotFoundException;
import models.domain.UserMessage;
import models.store.Message;
import services.interfaces.MessageService;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


public class StandardMessageService implements MessageService {

    private repository.interfaces.Message messageStore;


    @Inject
    public StandardMessageService(repository.interfaces.Message messageStore) {
        this.messageStore = messageStore; 
    }
    
    @Override
    public Message create(Message message) throws Exception {
        return messageStore.create(message);
    }

    @Override
    public Message find(UUID id) throws RecordNotFoundException {
        return messageStore.find(id);
    }

    @Override
    public List<UserMessage> findUserMessages(UUID chatSessionId, UUID userId) {
      
        List<UserMessage> m = new ArrayList<>();
        List<Message> seenBy = messageStore.findSeenBy(chatSessionId, userId);
        List<Message> notSeenBy = messageStore.findSeenBy(chatSessionId, userId);

        for(Message mi: seenBy)
            m.add(new UserMessage(mi, true));

        for(Message mi: notSeenBy)
            m.add(new UserMessage(mi, false));
        
        return m;
    }

    @Override
    public Boolean markMessageAsSeenFor(UUID messageId, UUID userId) throws RecordNotFoundException{
        messageStore.addUserToSeen(messageId, userId);
        return true;
    }
}
