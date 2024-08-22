package ru.veselkov.service;

import jakarta.annotation.Resource;
import jakarta.ejb.EJB;
import jakarta.ejb.SessionContext;
import jakarta.enterprise.context.Dependent;
import ru.veselkov.dao.DaoManager;
import ru.veselkov.dto.CustomerDto;
import ru.veselkov.dto.ProductDto;
import ru.veselkov.dto.RegistrationUser;
import ru.veselkov.mappers.Mapper;
import ru.veselkov.model.Customer;
import ru.veselkov.model.Product;
import ru.veselkov.model.enums.Role;

import java.util.ArrayList;
import java.util.List;


//@Stateless(name = "SimpleServiceBean")
@Dependent
public class SimpleServiceBean implements SimpleService {

    @EJB(lookup = "java:app/ipr-ejb/DaoManager")
    private DaoManager daoManager;

    @EJB(lookup = "java:global/ipr/ipr-ejb/TimerService")
    private TimerService timerService;

    @Override
    public void createCustomer(RegistrationUser registrationUser) {
        System.out.println("createCustomer");

        Customer newCustomer = new Customer();
        newCustomer.setUsername(registrationUser.getUsername());
        newCustomer.setFirstname(registrationUser.getFirstname());
        newCustomer.setSurname(registrationUser.getSurname());
        newCustomer.setPatronymic(registrationUser.getPatronymic());
        newCustomer.setUserRole(Role.valueOf(registrationUser.getUserRole()));
//        newCustomer.setCreateDate((java.sql.Date) new Date());
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

        System.out.println(newCustomer);
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


}
