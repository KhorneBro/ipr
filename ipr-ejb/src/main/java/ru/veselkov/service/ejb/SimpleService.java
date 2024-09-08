package ru.veselkov.service.ejb;

import ru.veselkov.dto.CustomerDto;
import ru.veselkov.dto.ProductDto;
import ru.veselkov.dto.RegistrationUser;

import java.io.Serializable;

public interface SimpleService extends Serializable {
//    CustomerDto findCustomerById(int id);

//    ProductDto findProductById(int id);
//
//    void saveProduct(ProductDto model);
//
//    void updateProduct(ProductDto model);
//
//    void deleteProduct(ProductDto model);

    void createCustomer(RegistrationUser registrationUser);
    void createCustomerTrans(RegistrationUser registrationUser, String method);

    void createProduct(ProductDto productDto);

    CustomerDto findById(int id);
}
