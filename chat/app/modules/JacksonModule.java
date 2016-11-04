package modules;


import com.google.inject.AbstractModule;
import modules.providers.JsonProvider;
import play.libs.Json;


public class JacksonModule extends AbstractModule {

    @Override
    protected void configure() {
        bind(Json.class).toProvider(JsonProvider.class);
    }
}
