package controllers;


import akka.stream.ActorMaterializer;
import exceptions.RecordNotFoundException;
import http.Response;
import http.actions.AuthenticatedRequestAction;
import http.actions.ExceptionMapperAction;
import play.mvc.Controller;
import play.mvc.Result;
import play.mvc.With;
import services.interfaces.ChatSessionService;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.UUID;

@Singleton
@With(ExceptionMapperAction.class)
public class ChatSessionController extends Controller {

    private final ChatSessionService chatSessionService;
    private final Response response;
    private ActorMaterializer flowMaterializer;

    @Inject
    public ChatSessionController(
            Response response,
            ChatSessionService chatSessionService,
            ActorMaterializer flowMaterializer
    
    ) {
        this.response = response;
        this.chatSessionService = chatSessionService;
 
    }

    @With({AuthenticatedRequestAction.class})
    public Result find(UUID id) throws RecordNotFoundException {
        //replace with message version with seen and unseen messages
        return ok(response.respond(chatSessionService.find(id)));
    }
}
