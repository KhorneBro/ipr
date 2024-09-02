package ru.veselkov.service.cdi;

import jakarta.inject.Inject;
import jakarta.inject.Named;
import ru.veselkov.service.cdi.api.CommonCdiInterface;

public class CallCdiNamedBeanService {

    @Inject
    @Named("app")
    private CommonCdiInterface commonCdiInterface;

    @Inject
    @Named("dep")
    private CommonCdiInterface commonCdiInterface2;

    @Inject
    @Named("ses")
    private CommonCdiInterface commonCdiInterface3;

    @Inject
    @Named("req")
    private CommonCdiInterface commonCdiInterface4;

    public void call() {
        System.out.println("CallCdiNamedBeanService");
        System.out.println(commonCdiInterface.getName());
        System.out.println(commonCdiInterface2.getName());
        System.out.println(commonCdiInterface3.getName());
        System.out.println(commonCdiInterface4.getName());
    }
}
