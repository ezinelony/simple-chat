package http.actions;


import http.ErrorResponse;
import http.Request;
import models.store.User;
import play.mvc.Http;
import play.mvc.Result;
import java.util.ArrayList;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;


public class UserValidationAction extends play.mvc.Action.Simple {

    public CompletionStage<Result> call(Http.Context ctx) {
        User u = new Request<>(ctx.request().body().asJson(), User.class).getRequest();
        return doCall(ctx, u);
    }
    
    protected boolean usernameIsValid(String username){
        return !username.trim().isEmpty();
    }

    protected CompletionStage<Result> doCall(Http.Context ctx, User u ) {
        CompletableFuture<Result> resultCompletableFuture = new CompletableFuture<>();
        ArrayList<String> errors = new ArrayList<>();
        if(!usernameIsValid(u.getUsername())){
            errors.add("Username cannot be empty");
        }

        if(!errors.isEmpty()) {
            ctx.response().setHeader("Content-Type", "application/json");
            String response = new ErrorResponse(errors).toJsonString();
            resultCompletableFuture.complete(badRequest(response));
            return resultCompletableFuture;
        }
        return delegate.call(ctx);
    }

}
