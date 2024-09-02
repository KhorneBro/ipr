package ru.veselkov.dao;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import jakarta.ejb.TransactionAttribute;
import jakarta.ejb.TransactionAttributeType;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.PersistenceUnit;
import jakarta.transaction.*;
import ru.veselkov.model.Customer;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

public class DaoManager {

    @PersistenceUnit
    private EntityManagerFactory entityManagerFactory;

    //    @PersistenceContext
    private EntityManager entityManager;
    private UserTransaction userTransaction;

//    @Resource
//    private SessionContext sessionContext;

    @PostConstruct
    private void init() {
        System.out.println("DaoManager init");
        entityManager = entityManagerFactory.createEntityManager();
        Context context = null;
        try {
            context = new InitialContext();
            userTransaction = (UserTransaction) context.lookup("java:comp/UserTransaction");
        } catch (NamingException e) {
            throw new RuntimeException(e);
        }
//        userTransaction = sessionContext.getUserTransaction();
    }

    @PreDestroy
    private void destroy() {
        System.out.println("DaoManager destroy");
        if (entityManager.isOpen()) {
            entityManager.close();
        }
    }


    public Customer findById(int id) {
        return entityManager.find(Customer.class, id);
    }

    @TransactionAttribute
    public <T> void persistT(T t) {
        try {
            userTransaction.begin();
            entityManager.persist(t);
            userTransaction.commit();
        } catch (NotSupportedException | HeuristicRollbackException | SystemException | HeuristicMixedException |
                 RollbackException e) {
            throw new RuntimeException(e);
        }
    }

    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    public <T> void persistTReqNew(T t) {
        try {
            userTransaction.begin();
            entityManager.persist(t);
            userTransaction.commit();
        } catch (NotSupportedException | HeuristicRollbackException | SystemException | HeuristicMixedException |
                 RollbackException e) {
            throw new RuntimeException(e);
        }
    }

    @TransactionAttribute(TransactionAttributeType.MANDATORY)
    public <T> void persistTMandatory(T t) {
        try {
            userTransaction.begin();
            entityManager.persist(t);
            userTransaction.commit();
        } catch (NotSupportedException | HeuristicRollbackException | SystemException | HeuristicMixedException |
                 RollbackException e) {
            throw new RuntimeException(e);
        }
    }

    @TransactionAttribute(TransactionAttributeType.SUPPORTS)
    public <T> void persistTSup(T t) {
        try {
            userTransaction.begin();
            entityManager.persist(t);
            userTransaction.commit();
        } catch (NotSupportedException | HeuristicRollbackException | SystemException | HeuristicMixedException |
                 RollbackException e) {
            throw new RuntimeException(e);
        }
    }


}
