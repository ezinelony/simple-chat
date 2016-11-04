package http.actions;


import http.ErrorResponse;
import http.Secured;
import play.mvc.Http;
import play.mvc.Result;

import java.util.ArrayList;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;


public class AuthenticatedRequestAction extends play.mvc.Action.Simple {

    public CompletionStage<Result> call(Http.Context ctx) {

        return doCall(ctx);
    }

    protected CompletionStage<Result> doCall(Http.Context ctx) {
        CompletableFuture<Result> resultCompletableFuture = new CompletableFuture<>();
        ArrayList<String> errors = new ArrayList<>();

        if(!Secured.cookieIsValid(ctx.session(), ctx.request())){
            errors.add("Requires login");
        }

        if(!errors.isEmpty()) {
            ctx.response().setHeader("Content-Type", "application/json");
            String response = new ErrorResponse(errors).toJsonString();
            resultCompletableFuture.complete(unauthorized(response));
            return resultCompletableFuture;
        }
        return delegate.call(ctx);
    }

}
