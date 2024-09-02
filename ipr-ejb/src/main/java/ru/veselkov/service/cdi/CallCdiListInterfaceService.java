package ru.veselkov.service.cdi;

import jakarta.enterprise.inject.Any;
import jakarta.enterprise.inject.Instance;
import jakarta.inject.Inject;
import ru.veselkov.service.cdi.api.CommonCdiInterface;

public class CallCdiListInterfaceService {

    @Inject
    @Any
    private Instance<CommonCdiInterface> commonCdiInterface;

    public void call() {
        System.out.println("CallCdiListInterfaceService");
        commonCdiInterface.forEach(a -> System.out.println(a.getName()));
    }
}
