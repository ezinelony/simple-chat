package controllers;


import exceptions.RecordNotFoundException;
import http.Request;
import http.Response;
import http.Secured;
import http.SimpleCookie;
import http.actions.AuthenticatedRequestAction;
import http.actions.ExceptionMapperAction;
import http.actions.UserValidationAction;
import models.store.User;
import play.mvc.BodyParser;
import play.mvc.Controller;
import play.mvc.Result;
import play.mvc.With;
import services.interfaces.UserService;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.UUID;

@Singleton
@With(ExceptionMapperAction.class)
public class UserController extends Controller {

    private final UserService userService;
    private final Response response;
    
    @Inject
    public UserController(Response response, UserService userService) {
        this.response = response;
        this.userService = userService;
    }


    @With({UserValidationAction.class})
    @BodyParser.Of(BodyParser.Json.class)
    public  Result login() throws RecordNotFoundException {
        User payload = new Request<>(request().body().asJson(), User.class).getRequest();
        User user  = userService.find(payload.getUsername());
        Secured.setCookie(session(), new SimpleCookie(user.getId(), request().remoteAddress()));
        
        return ok(response.respond(user));
    }


    @With(UserValidationAction.class)
    @BodyParser.Of(BodyParser.Json.class)
    public  Result create() throws Exception{
        User u = new Request<>(request().body().asJson(), User.class).getRequest();
        u.setId(UUID.randomUUID());
        u = userService.create(u);

        return created(response.respond(u));
    }

    @With({AuthenticatedRequestAction.class})
    public Result find(UUID id) throws RecordNotFoundException {
        return ok(response.respond(userService.find(id)));
    }
}
