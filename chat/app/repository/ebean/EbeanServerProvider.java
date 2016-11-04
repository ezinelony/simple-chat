package repository.ebean;


import com.avaje.ebean.Ebean;
import com.avaje.ebean.EbeanServer;

import javax.inject.Provider;


public class EbeanServerProvider implements Provider<EbeanServer> {

    //private final Ebean.  serverMgr = new ServerManager();
    @Override
    public EbeanServer get() {
        return Ebean.getDefaultServer();
    }
}