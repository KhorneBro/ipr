package ru.veselkov.dao;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import jakarta.annotation.Resource;
import jakarta.ejb.*;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.PersistenceUnit;
import jakarta.transaction.UserTransaction;
import ru.veselkov.model.Customer;

import java.io.Serializable;

@Stateless
@TransactionManagement(TransactionManagementType.CONTAINER)
//@TransactionManagement(TransactionManagementType.BEAN)
public class DaoManager implements Serializable {

//    @PersistenceUnit
//    private EntityManagerFactory entityManagerFactory;

    @PersistenceContext(unitName = "iprPersistenceUnit")
    private EntityManager entityManager;
//    private UserTransaction userTransaction;

//    @Resource
//    private SessionContext sessionContext;

//    @PostConstruct
//    private void init() {
//        entityManager = entityManagerFactory.createEntityManager();
//        Context context = null;
//        try {
//            context = new InitialContext();
//            userTransaction = (UserTransaction) context.lookup("java:comp/UserTransaction");
//        } catch (NamingException e) {
//            throw new RuntimeException(e);
//        }

//        userTransaction = sessionContext.getUserTransaction();
//    }

    public Customer fundById(int id) {
        return entityManager.find(Customer.class, id);
    }

    public <T> T updateT(T t) {
        entityManager.joinTransaction();
        entityManager.merge(t);
        return t;
    }

    public Customer findById(int id) {
        return entityManager.find(Customer.class, id);
    }

    @TransactionAttribute
    public <T> void persistT(T t) {
//        try {
//            userTransaction.begin();
        entityManager.persist(t);
//            userTransaction.commit();
//        } catch (NotSupportedException | HeuristicRollbackException | SystemException | HeuristicMixedException |
//                 RollbackException e) {
//            throw new RuntimeException(e);
//        }
    }

//    @PreDestroy
//    private void destroy() {
//
//        if (entityManager.isOpen()) {
//            entityManager.close();
//        }
//    }
}
