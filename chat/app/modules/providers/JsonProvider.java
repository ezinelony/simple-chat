package modules.providers;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.joda.JodaModule;
import com.google.inject.Inject;
import play.libs.Json;

import javax.inject.Provider;
import java.text.SimpleDateFormat;


public class JsonProvider implements Provider<Json> {
    
    
    private final ObjectMapper objectMapper;

    @Inject
    public JsonProvider(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @Override
    public Json get() {
        objectMapper.registerModule(new JodaModule());
        //objectMapper.registerModule(new DefaultScalaModule());
        objectMapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
        objectMapper.setDateFormat(new SimpleDateFormat());
        Json json = new Json();
        json.setObjectMapper(objectMapper);
        return json;
    }
}
