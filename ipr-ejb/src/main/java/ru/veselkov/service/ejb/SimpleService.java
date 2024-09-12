package ru.veselkov.service.ejb;

import ru.veselkov.dto.CustomerDto;
import ru.veselkov.dto.ProductDto;
import ru.veselkov.dto.RegistrationUser;

import java.io.Serializable;

public interface SimpleService extends Serializable {

    void createCustomer(RegistrationUser registrationUser);

    void createCustomerTrans(RegistrationUser registrationUser, String method, String method2);

    void createProduct(ProductDto productDto);

    CustomerDto findById(int id);

    void changeCustomer(int id);
}
