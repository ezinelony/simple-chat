package modules;


import com.google.inject.AbstractModule;
import play.libs.akka.AkkaGuiceSupport;


public class MailerModule extends AbstractModule implements AkkaGuiceSupport{

    @Override
    protected void configure() {
       // bind(MailerClient.class).to(SMTPMailer.class);

        //bindActor(ChatSessionMessagePusherActor.class, "email-actor");
    }
}
