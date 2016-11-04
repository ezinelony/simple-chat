package modules;


import com.google.inject.AbstractModule;
import services.*;
import services.interfaces.*;


public class ServiceModule extends AbstractModule {

    @Override
    protected void configure() {
        //Bind user interface to concrete user
        bind(UserService.class).to(StandardUserService.class);
        bind(ChatSessionService.class).to(StandardChatSessionService.class);
        bind(MessageService.class).to(StandardMessageService.class);

        try {
            bind(CacheService.class).to(StandardCacheService.class);
        }catch (play.api.cache.EhCacheExistsException e){
            
        }
    }
}
