package ru.veselkov.service.remote;

import jakarta.ejb.EJB;
import jakarta.ejb.Stateless;
import ru.veselkov.iprremote.service.RemoteServiceIpr;
import ru.veselkov.iprremote.service.ServiceBean;


import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import java.util.Hashtable;

@Stateless
public class RemoteCallService {

    public void call() throws NamingException {
        System.out.println("ipr call()");
        final Hashtable jndiProperties = new Hashtable();
        jndiProperties.put(Context.INITIAL_CONTEXT_FACTORY, "org.wildfly.naming.client.WildFlyInitialContextFactory");
        jndiProperties.put(Context.PROVIDER_URL, "remote+http://localhost:8081/");

        final Context context = new InitialContext(jndiProperties);
        RemoteServiceIpr remoteServiceIpr = (RemoteServiceIpr) context.lookup("ejb:/target/ServiceBean!" + RemoteServiceIpr.class.getName());
        remoteServiceIpr.callRemote();
        context.close();
        System.out.println("ipr call() end");
    }

    public void call(long id) throws NamingException {
        final Hashtable jndiProperties = new Hashtable();
        jndiProperties.put(Context.INITIAL_CONTEXT_FACTORY, "org.wildfly.naming.client.WildFlyInitialContextFactory");
        jndiProperties.put(Context.PROVIDER_URL, "remote+http://localhost:8081/");

        final Context context = new InitialContext(jndiProperties);
        RemoteServiceIpr remoteServiceIpr = (RemoteServiceIpr) context.lookup("ejb:/target/ServiceBean!" + RemoteServiceIpr.class.getName());
        remoteServiceIpr.callRemote(null);
    }
}
