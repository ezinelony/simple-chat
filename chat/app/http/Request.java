package http;


import com.fasterxml.jackson.databind.JsonNode;
import play.libs.Json;


public class Request<T> {

    private  T request;

    public Request(JsonNode json, Class<T> clazz) {
        request = Json.fromJson(json, clazz);
    }
    
    public T getRequest() {
        return request;
    }
}
