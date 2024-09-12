package ru.veselkov.service.remote;

import jakarta.ejb.Stateless;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import ru.veselkov.iprremote.service.RemoteServiceIpr;

import javax.naming.NamingException;

@Stateless
public class RemoteCallService {

    @Inject
    @Named("RemoteServiceIpr")
    private RemoteServiceIpr remoteServiceIpr;

    public void call() throws NamingException {
        System.out.println("ipr call()");
        remoteServiceIpr.callRemote();
        System.out.println("ipr call() end");
    }

    public void call(int id) throws NamingException {
       remoteServiceIpr.callRemote(id);
    }
}
