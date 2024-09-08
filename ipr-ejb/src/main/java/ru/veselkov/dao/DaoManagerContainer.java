package ru.veselkov.dao;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import jakarta.ejb.*;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import ru.veselkov.model.Customer;

@Stateless
@TransactionManagement(TransactionManagementType.CONTAINER)
public class DaoManagerContainer {
    @PersistenceContext
    private EntityManager entityManager;

    @PostConstruct
    private void init() {
        System.out.println("DaoManagerContainer init");
    }

    @PreDestroy
    private void destroy() {
        System.out.println("DaoManagerContainer destroy");
    }

    @TransactionAttribute(TransactionAttributeType.NEVER)
    public <T> void persistTWithMethod(T t, String method) {
        if (t == null || method == null || method.length() < 1) {
            return;
        }
        switch (method) {
            case "REQUIRED" -> persistTReq(t);
            case "REQUIRES_NEW" -> persistTReqNew(t);
            case "MANDATORY" -> persistTMandatory(t);
            case "NOT_SUPPORTED" -> persistTNotSup(t);
            case "SUPPORTS" -> persistTSup(t);
            case "NEVER" -> persistTNever(t);
            default -> System.out.println("Error");
        }
    }

    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public <T> void persistTReq(T t) {
        System.out.println("REQUIRED");
        entityManager.persist(t);
    }


    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    public <T> void persistTReqNew(T t) {
        System.out.println("REQUIRES_NEW");
        entityManager.persist(t);
    }

    @TransactionAttribute(TransactionAttributeType.MANDATORY)
    public <T> void persistTMandatory(T t) {
        System.out.println("MANDATORY");
        entityManager.persist(t);
    }

    @TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
    public <T> void persistTNotSup(T t) {
        System.out.println("NOT_SUPPORTED");
        entityManager.persist(t);
    }

    @TransactionAttribute(TransactionAttributeType.SUPPORTS)
    public <T> void persistTSup(T t) {
        System.out.println("SUPPORTS");
        entityManager.persist(t);
    }

    @TransactionAttribute(TransactionAttributeType.NEVER)
    public <T> void persistTNever(T t) {
        System.out.println("SUPPORTS");
        entityManager.persist(t);
    }

    public <T> T merge(T t) {
        System.out.println("merge");
        return entityManager.merge(t);
    }

    @TransactionAttribute
    public void changeCustomer(int id) {
        Customer customer = entityManager.find(Customer.class, id);

        if (customer == null) {
            throw new IllegalArgumentException(" Customer is null ");
        }

        customer.setFirstname("ChangedNameByContainer");
    }
}
