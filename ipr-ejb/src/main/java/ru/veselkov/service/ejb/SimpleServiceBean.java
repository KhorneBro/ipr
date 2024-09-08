package ru.veselkov.service.ejb;

import jakarta.ejb.EJB;
import jakarta.inject.Inject;
import ru.veselkov.dao.DaoManagerBean;
import ru.veselkov.dao.DaoManagerContainer;
import ru.veselkov.dto.CustomerDto;
import ru.veselkov.dto.ProductDto;
import ru.veselkov.dto.RegistrationUser;
import ru.veselkov.mappers.Mapper;
import ru.veselkov.model.Customer;
import ru.veselkov.model.Product;
import ru.veselkov.model.enums.Role;
import ru.veselkov.service.cdi.impls.RequestScopeCdiBean;

import java.util.ArrayList;
import java.util.List;


//@Stateless(name = "SimpleServiceBean")
//@Dependent
public class SimpleServiceBean implements SimpleService {

    //    @EJB(name = "DaoManager")
    private DaoManagerBean daoManager;

    @EJB
    private DaoManagerContainer daoManagerContainer;

    @Inject
    private RequestScopeCdiBean requestScopeCdiBean;

    @EJB(lookup = "java:global/ipr/ipr-ejb/TimerServiceBean")
    private TimerServiceBean timerService;

    @Override
    public void createCustomer(RegistrationUser registrationUser) {
        System.out.println("createCustomer");

        Customer newCustomer = convertDtoInEntity(registrationUser);
        Product product = new Product();
        product.setProductType("productDto.getProductType()");
        product.setProductName("productDto.getProductName()");
        product.setProductPrice(18);
        product.setCustomer(newCustomer);
        List<Product> products = new ArrayList<>();
        products.add(product);
        newCustomer.setProductsByCustomerId(products);

        timerService.createTimer(10000L);

        System.out.println("вызов перед persistT");
        daoManager.persistT(newCustomer);
        System.out.println("вызов после persistT");
//        Customer newCustomer2 = convertDtoInEntity(registrationUser);
        newCustomer.setFirstname("Cdi");
        requestScopeCdiBean.createTransactional(newCustomer);

        System.out.println(newCustomer);
    }

    private Customer convertDtoInEntity(RegistrationUser registrationUser) {
        Customer newCustomer = new Customer();
        newCustomer.setUsername(registrationUser.getUsername());
        newCustomer.setFirstname(registrationUser.getFirstname());
        newCustomer.setSurname(registrationUser.getSurname());
        newCustomer.setPatronymic(registrationUser.getPatronymic());
        newCustomer.setUserRole(Role.valueOf(registrationUser.getUserRole()));
        return newCustomer;
    }

    @Override
    public void createProduct(ProductDto productDto) {
        Product product = new Product();
        product.setProductType(productDto.getProductType());
        product.setProductName(productDto.getProductName());
        product.setProductPrice(productDto.getProductPrice());

        System.out.println(productDto);
        System.out.println(product);

        daoManager.persistT(product);
        System.out.println(product);
    }

    @Override
    public CustomerDto findById(int id) {
        Customer customerFoundById = daoManager.findById(id);
        System.out.println("customerFyndedById");
        System.out.println(customerFoundById);
        return Mapper.map(customerFoundById);
    }

    @Override
    public void createCustomerTrans(RegistrationUser registrationUser, String method) {
        Customer newCustomer = new Customer();
        newCustomer.setUsername(registrationUser.getUsername());
        newCustomer.setFirstname(registrationUser.getFirstname());
        newCustomer.setSurname(registrationUser.getSurname());
        newCustomer.setPatronymic(registrationUser.getPatronymic());
        newCustomer.setUserRole(Role.valueOf(registrationUser.getUserRole()));
        newCustomer.setTransaction(method);


        if (method == null || method.length() < 1) {
            return;
        }

        daoManagerContainer.persistTWithMethod(newCustomer, method);

//        switch (method) {
//            case "REQUIRED" -> daoManagerContainer.persistTReq(newCustomer);
//            case "REQUIRES_NEW" -> daoManagerContainer.persistTReqNew(newCustomer);
//            case "MANDATORY" -> daoManagerContainer.persistTMandatory(newCustomer);
//            case "NOT_SUPPORTED" -> daoManagerContainer.persistTNotSup(newCustomer); //здесь ошибка
//            case "SUPPORTS" -> daoManagerContainer.persistTSup(newCustomer);
//            case "NEVER" -> daoManagerContainer.persistTNever(newCustomer); //здесь ошибка
//            default -> System.out.println("Error");
//        }

        System.out.println(newCustomer);
    }
}
