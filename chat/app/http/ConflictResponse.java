package http;


import static play.mvc.Results.status;


public class ConflictResponse {

    public play.mvc.Result response(){
        return  status(409, "The entity you are attempting to create already exists");
    }
}
