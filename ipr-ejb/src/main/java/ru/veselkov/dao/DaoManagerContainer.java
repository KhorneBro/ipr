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

    public <T> void persistTWithMethod(T t, String method1, String method2) {
        if (t == null || method1 == null || method1.length() < 1
                || method2 == null || method2.length() < 1) {
            return;
        }
        selectTransactionalType(t, method1, method2);
    }

    public <T> void selectTransactionalType(T t, String method1, String method2) {
        switch (method1) {
            case "REQUIRED" -> persistTReq(t, method2);
            case "REQUIRES_NEW" -> persistTReqNew(t, method2);
            case "MANDATORY" -> persistTMandatory(t, method2);
            case "NOT_SUPPORTED" -> persistTNotSup(t, method2);
            case "SUPPORTS" -> persistTSup(t, method2);
            case "NEVER" -> persistTNever(t, method2);
            default -> System.out.println("Error");
        }
    }

    private <T> void selectTransactionalType(T t, String method1) {
        switch (method1) {
            case "REQUIRED" -> persistTReq(t);
            case "REQUIRES_NEW" -> persistTReqNew(t);
            case "MANDATORY" -> persistTMandatory(t);
            case "NOT_SUPPORTED" -> persistTNotSup(t);
            case "SUPPORTS" -> persistTSup(t);
            case "NEVER" -> persistTNever(t);
            default -> System.out.println("Error");
        }
    }

    /**
     * Если клиент работает в транзакции и вызывает метод Enterprise-бина, метод выполняется в транзакции клиента.
     * Если клиент не связан с транзакцией, контейнер запускает новую транзакцию перед запуском метода.
     *
     * @param t
     * @param <T>
     */
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public <T> void persistTReq(T t, String method2) {
        System.out.println("REQUIRED");
        selectTransactionalType(t, method2);
        entityManager.persist(t);
    }

    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public <T> void persistTReq(T t) {
        System.out.println("REQUIRED");
        entityManager.persist(t);
    }

    /**
     * Если клиент работает в транзакции и вызывает метод Enterprise-бина, контейнер выполняет следующие шаги:
     * Приостанавливает транзакцию клиента
     * Запускает новую транзакцию
     * Делегирует вызов метода
     * Возобновляет транзакцию клиента после завершения метода
     * Если клиент не связан с транзакцией, контейнер запускает новую транзакцию перед запуском метода.
     *
     * @param t
     * @param method2
     * @param <T>
     */
    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    public <T> void persistTReqNew(T t, String method2) {
        System.out.println("REQUIRES_NEW");
        selectTransactionalType(t, method2);
        entityManager.persist(t);
    }

    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    public <T> void persistTReqNew(T t) {
        System.out.println("REQUIRES_NEW");
        entityManager.persist(t);
    }

    /**
     * Если клиент работает в транзакции и вызывает метод Enterprise-бина, метод выполняется в транзакции клиента.
     * Если клиент не связан с транзакцией, контейнер выбрасывает исключение TransactionRequiredException.
     *
     * @param t
     * @param method2
     * @param <T>
     */
    @TransactionAttribute(TransactionAttributeType.MANDATORY)
    public <T> void persistTMandatory(T t, String method2) {
        System.out.println("MANDATORY");
        selectTransactionalType(t, method2);
        entityManager.persist(t);
    }

    @TransactionAttribute(TransactionAttributeType.MANDATORY)
    public <T> void persistTMandatory(T t) {
        System.out.println("MANDATORY");
        entityManager.persist(t);
    }

    /**
     * Если клиент работает в транзакции и вызывает метод Enterprise-бина, контейнер приостанавливает транзакцию клиента перед вызовом метода.
     * После завершения метода контейнер возобновляет транзакцию клиента.
     * Если клиент не связан с транзакцией, контейнер не запускает новую транзакцию перед запуском метода.
     *
     * @param t
     * @param method2
     * @param <T>
     */
    @TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
    public <T> void persistTNotSup(T t, String method2) {
        System.out.println("NOT_SUPPORTED");
        selectTransactionalType(t, method2);
        entityManager.persist(t);
    }

    @TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
    public <T> void persistTNotSup(T t) {
        System.out.println("NOT_SUPPORTED");
        entityManager.persist(t);
    }

    /**
     * Если клиент работает в транзакции и вызывает метод Enterprise-бина, метод выполняется в транзакции клиента.
     * Если клиент не связан с транзакцией, контейнер не запускает новую транзакцию перед запуском метода.
     *
     * @param t
     * @param method2
     * @param <T>
     */
    @TransactionAttribute(TransactionAttributeType.SUPPORTS)
    public <T> void persistTSup(T t, String method2) {
        System.out.println("SUPPORTS");
        selectTransactionalType(t, method2);
        entityManager.persist(t);
    }

    @TransactionAttribute(TransactionAttributeType.SUPPORTS)
    public <T> void persistTSup(T t) {
        System.out.println("SUPPORTS");
        entityManager.persist(t);
    }

    /**
     * Если клиент работает в транзакции и вызывает метод Enterprise-бина, контейнер генерирует RemoteException.
     * Если клиент не связан с транзакцией, контейнер не запускает новую транзакцию перед запуском метода.
     *
     * @param t
     * @param method2
     * @param <T>
     */
    @TransactionAttribute(TransactionAttributeType.NEVER)
    public <T> void persistTNever(T t, String method2) {
        System.out.println("NEVER");
        entityManager.persist(t);
        selectTransactionalType(t, method2);
    }

    @TransactionAttribute(TransactionAttributeType.NEVER)
    public <T> void persistTNever(T t) {
        System.out.println("NEVER");
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
