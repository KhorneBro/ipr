package ru.veselkov.mappers;

import ru.veselkov.dto.CustomerDto;
import ru.veselkov.dto.ProductDto;
import ru.veselkov.model.Customer;
import ru.veselkov.model.Product;

import java.util.ArrayList;
import java.util.List;

public class Mapper {

    public static Product map(ProductDto productDto) {
        Product product = new Product();

        product.setProductId(productDto.getId());
        product.setProductName(productDto.getProductName());
        product.setProductPrice(productDto.getProductPrice());
        product.setProductType(productDto.getProductType());

        return product;
    }

    public static Customer map(CustomerDto customerDto) {
        Customer customer = new Customer();

        customer.setCustomerId(customerDto.getId());
        customer.setUserRole(customerDto.getUserRole());
        customer.setUsername(customerDto.getUsername());
        customer.setFirstname(customerDto.getFirstname());
        customer.setSurname(customerDto.getSurname());
        customer.setPatronymic(customerDto.getPatronymic());

        return customer;
    }

    public static ProductDto map(Product product) {
        ProductDto productDto = new ProductDto();

        productDto.setId(productDto.getId());
        productDto.setProductName(productDto.getProductName());
        productDto.setProductPrice(productDto.getProductPrice());
        productDto.setProductType(productDto.getProductType());
        productDto.setUserid(productDto.getUserid());

        return productDto;
    }

    public static CustomerDto map(Customer customer) {
        CustomerDto customerDto = new CustomerDto();

        customerDto.setId(customer.getCustomerId());
        customerDto.setUserRole(customer.getUserRole());
        customerDto.setUsername(customer.getUsername());
        customerDto.setFirstname(customer.getFirstname());
        customerDto.setSurname(customer.getSurname());
        customerDto.setPatronymic(customer.getPatronymic());
        customerDto.setProductsByCustomerId(map(customer.getProductsByCustomerId()));

        return customerDto;
    }

    public static List<Product> mapProduct(List<ProductDto> productDtos) {
        if (productDtos == null || productDtos.isEmpty()) {
            return new ArrayList<>();
        }
        return productDtos.stream().map(Mapper::map).toList();
    }

    public static List<ProductDto> map(List<Product> products) {
        if (products == null || products.isEmpty()) {
            return new ArrayList<>();
        }
        return products.stream().map(Mapper::map).toList();
    }
}
