package http.actions;


import http.ErrorResponse;
import http.Request;
import models.store.Message;
import play.mvc.Http;
import play.mvc.Result;

import java.util.ArrayList;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;


public class MessageValidationAction extends play.mvc.Action.Simple {

    public CompletionStage<Result> call(Http.Context ctx) {
        Message msg = new Request<>(ctx.request().body().asJson(), Message.class).getRequest();
        return doCall(ctx, msg);
    }
    

    protected CompletionStage<Result> doCall(Http.Context ctx, Message message ) {
        CompletableFuture<Result> resultCompletableFuture = new CompletableFuture<>();
        ArrayList<String> errors = new ArrayList<>();
        if(message.getBody().trim().isEmpty()){
            errors.add("Message body must not be empty");
        }

        if(message.getChatSessionId() == null){
            errors.add("Chat session id must be given");
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
