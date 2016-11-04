package http;


import com.fasterxml.jackson.databind.JsonNode;
import play.libs.Json;

import javax.inject.Inject;


public class Response {

    private Json json;
    @Inject
    public Response(Json json){ this.json = json;}
    
    private JsonNode response;

    public <T> JsonNode respond(T model) {
        response = json.toJson(model);
        return response;
    }
}
