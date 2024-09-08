package ru.veselkov.service.cdi.impls;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import jakarta.enterprise.context.SessionScoped;
import jakarta.enterprise.inject.Produces;
import jakarta.inject.Named;
import ru.veselkov.service.cdi.api.CommonCdiInterface;

import java.io.Serializable;

@SessionScoped
@Named("ses")
public class SessionScopeCdiBean implements Serializable, CommonCdiInterface {

    private int i = 0;

    public int getI() {
        return i;
    }

    @Override
    public String getName() {
        return "SessionScopeCdiBean";
    }

    @Override
    @Produces
    @Named("SesSay")
    public String say() {
        return "SessionScopeCdiBean say";
    }

    public void setI(int i) {
        this.i = this.i + i;
    }

    @PostConstruct
    private void constr() {
        System.out.println("SessionScopeCdiBean created i = " + i);
    }

    @PreDestroy
    private void destr() {
        System.out.println("SessionScopeCdiBean destroyed i = " + i);
    }

}
