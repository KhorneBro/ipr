package ru.veselkov.service.cdi.impls;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import jakarta.enterprise.context.Dependent;
import jakarta.enterprise.inject.Produces;
import jakarta.inject.Named;
import ru.veselkov.service.cdi.api.CommonCdiInterface;

import java.io.Serializable;

@Dependent
@Named("dep")
public class DependentCdiBean implements Serializable, CommonCdiInterface {

    private int i = 0;

    public int getI() {
        return i;
    }

    @Override
    public String getName() {
        return "DependentCdiBean";
    }

    public void setI(int i) {
        this.i = this.i + i;
    }

    @Produces
    public String say() {
        return "say hello DependentCdiBean";
    }

    @PostConstruct
    private void constr() {
        System.out.println("DependentCdiBean created i = " + i);
    }

    @PreDestroy
    private void destr() {
        System.out.println("DependentCdiBean destroyed i = " + i);
    }
}
