package http.actions;

import com.fasterxml.jackson.databind.JsonNode;
import exceptions.RecordConflictException;
import exceptions.RecordNotFoundException;
import play.libs.Json;
import play.mvc.Http;
import play.mvc.Result;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;

public class ExceptionMapperAction extends play.mvc.Action.Simple {

    private JsonNode toJson(String errors) {
        HashMap<String, ArrayList<String>> e = new HashMap<>();
        ArrayList list = new ArrayList<>();
        list.add(errors);
        e.put("errors", list);
        return Json.toJson(e);
    }
    
    private CompletableFuture<Result> mapRecordNotFoundException(Throwable t,  CompletableFuture<Result> resultCompletableFuture) {
        resultCompletableFuture.complete(notFound(toJson(t.getMessage())));
        return  resultCompletableFuture;
    }

    private CompletableFuture<Result> mapRecordConflictException(Throwable t,  CompletableFuture<Result> resultCompletableFuture) {
        resultCompletableFuture.complete(status(409, toJson(t.getMessage())));
        return  resultCompletableFuture;
    }

    private CompletableFuture<Result> mapDefaultException(Throwable t, CompletableFuture<Result> resultCompletableFuture) {
        resultCompletableFuture.complete(status(500, toJson("Something happened try again later "+ t.getMessage())));
        return  resultCompletableFuture;
    }
    
    @Override
    public CompletionStage<Result> call(Http.Context ctx) {
        try{
            return delegate.call(ctx);
        }catch (Throwable t){
            ctx.response().setHeader("Content-Type", "application/json");
          
            CompletableFuture<Result> resultCompletableFuture = new CompletableFuture<>();

            if(t instanceof RecordNotFoundException)
                return  mapRecordNotFoundException(t, resultCompletableFuture);

            if(t instanceof RecordConflictException) 
                return mapRecordConflictException(t, resultCompletableFuture);
            
            return mapDefaultException(t, resultCompletableFuture);
        }
    }
}
