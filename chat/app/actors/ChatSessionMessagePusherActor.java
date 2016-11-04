package actors;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.UntypedActor;
import akka.event.Logging;
import akka.event.LoggingAdapter;
import com.google.inject.Inject;
import models.store.Message;
import services.interfaces.MessageService;



public class ChatSessionMessagePusherActor extends UntypedActor {
    LoggingAdapter log = Logging.getLogger(getContext().system(), this);

    
    private final MessageService messageService;
    private final ActorSystem system;
    private final ActorRef out;
    
    @Inject
    public ChatSessionMessagePusherActor(
            MessageService messageService,
            ActorRef out,
            ActorSystem system
    ) {
        this.messageService = messageService; 
        this.system = system;
        this.out = out;
    }


    public void onReceive(Object message) throws Exception {
        
        if(message instanceof Message){
                //get connected sockets and send them a message
        
        }
        else if (message instanceof String) {
            out.tell("I received your message: " + message, self());
        }
    }
}
