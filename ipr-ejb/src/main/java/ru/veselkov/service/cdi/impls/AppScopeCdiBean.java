package ru.veselkov.service.cdi.impls;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.inject.Produces;
import jakarta.inject.Named;
import ru.veselkov.service.cdi.api.CommonCdiInterface;
import ru.veselkov.service.cdi.qualifiers.AppQualifier;

@ApplicationScoped
@Named("app")
public class AppScopeCdiBean implements CommonCdiInterface {

    private int i = 0;

    public int getI() {
        return i;
    }

    @Override
    public String getName() {
        return "AppScopeCdiBean";
    }

    public void setI(int i) {
        this.i = this.i + i;
    }

    @Override
    @Produces
    @AppQualifier
    public String say() {
        return "AppScopeCdiBean say";
    }

    @PostConstruct
    private void constr() {
        System.out.println("AppScopeCdiBean created i = " + i);
    }

    @PreDestroy
    private void destr() {
        System.out.println("AppScopeCdiBean destroyed i = " + i);
    }
}
