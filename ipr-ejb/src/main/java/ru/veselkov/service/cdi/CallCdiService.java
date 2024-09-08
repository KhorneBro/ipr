package ru.veselkov.service.cdi;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import ru.veselkov.service.cdi.impls.AppScopeCdiBean;
import ru.veselkov.service.cdi.impls.DependentCdiBean;
import ru.veselkov.service.cdi.impls.RequestScopeCdiBean;
import ru.veselkov.service.cdi.impls.SessionScopeCdiBean;
import ru.veselkov.service.cdi.qualifiers.ReqQualifier;

import java.io.Serializable;

@ApplicationScoped
public class CallCdiService implements Serializable {

    @Inject
    private AppScopeCdiBean appScopeCdiBean;

    @Inject
    private DependentCdiBean dependentCdiBean;

    @Inject
    private RequestScopeCdiBean requestScopeCdiBean;

    @Inject
    private SessionScopeCdiBean sessionScopeCdiBean;

    @Inject
    @ReqQualifier
    private String say;


    private int i = 1;

    public void call() {
        System.out.println("CallService i = " + i);
        appScopeCdiBean.setI(i++);
        dependentCdiBean.setI(i++);
        requestScopeCdiBean.setI(i++);
        sessionScopeCdiBean.setI(i++);

        System.out.println("appScopeCdiBean.getI() = " + appScopeCdiBean.getI());
        System.out.println("dependentCdiBean.getI() = " + dependentCdiBean.getI());
        System.out.println("requestScopeCdiBean.getI() = " + requestScopeCdiBean.getI());
        System.out.println("sessionScopeCdiBean.getI() = " + sessionScopeCdiBean.getI());
        System.out.println(say);
    }

    public void say() {

    }
}
