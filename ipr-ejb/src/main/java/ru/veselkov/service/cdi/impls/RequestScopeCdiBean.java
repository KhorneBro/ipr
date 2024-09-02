package ru.veselkov.service.cdi.impls;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Named;
import ru.veselkov.service.cdi.api.CommonCdiInterface;

@RequestScoped
@Named("req")
public class RequestScopeCdiBean implements CommonCdiInterface {

    private int i = 0;

    public int getI() {
        return i;
    }

    @Override
    public String getName() {
        return "RequestScopeCdiBean";
    }

    public void setI(int i) {
        this.i = this.i + i;
    }

    @PostConstruct
    private void constr() {
        System.out.println("RequestScopeCdiBean created i = " + i);
    }

    @PreDestroy
    private void destr() {
        System.out.println("RequestScopeCdiBean destroyed i = " + i);
    }
}
