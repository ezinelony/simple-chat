package modules;


import com.google.inject.AbstractModule;
import repository.*;
import repository.interfaces.*;


public class StoreModule extends AbstractModule {

    @Override
    protected void configure() {
        //Bind user interface to concrete user
        bind(User.class).to(UserDao.class);
        bind(Message.class).to(MessageDao.class);
        bind(ChatSession.class).to(ChatSessionDao.class);
        
        
    }
}
