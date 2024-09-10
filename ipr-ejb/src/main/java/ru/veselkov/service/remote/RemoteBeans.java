package ru.veselkov.service.remote;


import jakarta.enterprise.context.Dependent;
import jakarta.enterprise.inject.Produces;
import jakarta.inject.Named;
import ru.veselkov.iprremote.service.RemoteServiceIpr;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import java.util.Hashtable;

@Dependent
public class RemoteBeans {

    @Produces
    @Dependent
    @Named("RemoteServiceIpr")
    public RemoteServiceIpr getRemoteService() {
        final Hashtable jndiProperties = new Hashtable();
        jndiProperties.put(Context.INITIAL_CONTEXT_FACTORY, "org.wildfly.naming.client.WildFlyInitialContextFactory");
        jndiProperties.put(Context.PROVIDER_URL, "remote+http://localhost:8081/");

        final Context context;
        try {
            context = new InitialContext(jndiProperties);
            return (RemoteServiceIpr) context.lookup("ejb:/target/ServiceBean!" + RemoteServiceIpr.class.getName());
        } catch (NamingException e) {
            throw new RuntimeException(e);
        }

    }
}
