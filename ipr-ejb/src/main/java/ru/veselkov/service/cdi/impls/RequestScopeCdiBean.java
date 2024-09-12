package ru.veselkov.service.cdi.impls;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import jakarta.ejb.EJB;
import jakarta.ejb.TransactionAttribute;
import jakarta.ejb.TransactionAttributeType;
import jakarta.enterprise.context.RequestScoped;
import jakarta.enterprise.inject.Produces;
import jakarta.inject.Named;
import ru.veselkov.dao.DaoManagerContainer;
import ru.veselkov.model.Customer;
import ru.veselkov.service.cdi.api.CommonCdiInterface;
import ru.veselkov.service.cdi.qualifiers.ReqQualifier;

@RequestScoped
@Named("req")
public class RequestScopeCdiBean implements CommonCdiInterface {

    @EJB
    private DaoManagerContainer daoManagerContainer;

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

    @Override
    @Produces
    @ReqQualifier
    public String say() {
        return "RequestScopeCdiBean say";
    }

    @PostConstruct
    private void constr() {
        System.out.println("RequestScopeCdiBean created i = " + i);
    }

    @PreDestroy
    private void destr() {
        System.out.println("RequestScopeCdiBean destroyed i = " + i);
    }

    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public void createTransactional(Customer newCustomer) {
        daoManagerContainer.merge(newCustomer);
    }
}
