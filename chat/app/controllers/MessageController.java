package controllers;


import exceptions.RecordNotFoundException;
import http.Request;
import http.Response;
import http.Secured;
import http.actions.AuthenticatedRequestAction;
import http.actions.ExceptionMapperAction;
import http.actions.MessageValidationAction;
import models.store.ChatSession;
import models.store.Message;
import models.store.User;
import play.mvc.BodyParser;
import play.mvc.Controller;
import play.mvc.Result;
import play.mvc.With;
import services.interfaces.MessageService;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.UUID;

@Singleton
@With(ExceptionMapperAction.class)
public class MessageController extends Controller {

    private final MessageService messageService;
    private final Response response;

    @Inject
    public MessageController(Response response, MessageService messageService) {
        this.response = response;
        this.messageService = messageService;
    }

    @With({AuthenticatedRequestAction.class, MessageValidationAction.class})
    @BodyParser.Of(BodyParser.Json.class)
    public  Result create() throws Exception{
        
        Message msg = new Request<>(request().body().asJson(), Message.class).getRequest();
        msg.setId(UUID.randomUUID());
        
        User u = new User();
        ChatSession cs = new ChatSession();
        
        cs.setId(msg.getChatSessionId());
        u.setId(Secured.getCookie(session(), request()).get().userId());
        msg.setCreator(u);
        msg.setSeenBy(new ArrayList<>(Arrays.asList(u)));
        messageService.create(msg);
        
        //tell actors about the message
        //maybe set the message to seen
        return created(response.respond(msg));
    }

    @With({AuthenticatedRequestAction.class})
    public Result find(UUID id) throws RecordNotFoundException {
        return ok(response.respond(messageService.find(id)));
    }

    @With({AuthenticatedRequestAction.class})
    public Result markAsSeen(UUID messageId) throws RecordNotFoundException {
        return ok(response.respond(messageService.markMessageAsSeenFor(
                messageId, Secured.getCookie(session(), request()
                ).get().userId())
        ));
    }
}
