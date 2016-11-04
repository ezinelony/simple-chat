package modules;


import com.google.inject.AbstractModule;


public class ApplicationModule extends AbstractModule {

    @Override
    protected void configure() {
        bind(server.ChatServer.class).asEagerSingleton();
    }
}
