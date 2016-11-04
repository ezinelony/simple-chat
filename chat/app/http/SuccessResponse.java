package http;


import play.libs.Json;

import javax.inject.Inject;
import java.util.HashMap;

import static play.mvc.Results.ok;


public class SuccessResponse {

    private Json json;
    @Inject
    public SuccessResponse(Json json){ this.json = json;}
    public SuccessResponse(){ this.json = new Json();}
    public play.mvc.Result response(){
        HashMap<String, Boolean> result = new HashMap<>();
        result.put("success", true);
        return ok(json.toJson(result));
    }
}
