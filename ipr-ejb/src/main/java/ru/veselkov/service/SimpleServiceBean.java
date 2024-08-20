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

    @EJB
    private DaoManager daoManager;

    @EJB
    private TimerService timerService;

//    @Resource
//    private UserTransaction transaction;

//    @Override
//    public CustomerDto findCustomerById(int id) {
//        return map(dao.findCustomerById(id));
//    }
//
//    @Override
//    public ProductDto findProductById(int id) {
//        return map(dao.findProductById(id));
//    }
//
//    @Override
//    public void saveProduct(ProductDto productDto) {
//        dao.saveProduct(map(productDto));
//    }
//
//    @Override
//    public void updateProduct(ProductDto productDto) {
//        dao.updateProduct(map(productDto));
//    }
//
//    @Override
//    public void deleteProduct(ProductDto productDto) {
//        dao.deleteProduct(map(productDto));
//    }

    @Override
    public void createCustomer(RegistrationUser registrationUser) {

//        Customer old = daoManager.findById(1);
//        System.out.println(old);

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

        daoManager.persistT(newCustomer);

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
