package ru.veselkov.dto;

import ru.veselkov.model.Product;
import ru.veselkov.model.enums.Role;

import java.io.Serializable;
import java.util.List;

public class CustomerDto implements Serializable {

    private int id;
    private String username;
    private String firstname;
    private String surname;
    private String patronymic;
    private Role userRole;

    private List<ProductDto> productsByCustomerId;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getPatronymic() {
        return patronymic;
    }

    public void setPatronymic(String patronymic) {
        this.patronymic = patronymic;
    }

    public Role getUserRole() {
        return userRole;
    }

    public void setUserRole(Role userRole) {
        this.userRole = userRole;
    }

    public List<ProductDto> getProductsByCustomerId() {
        return productsByCustomerId;
    }

    public void setProductsByCustomerId(List<ProductDto> productsByCustomerId) {
        this.productsByCustomerId = productsByCustomerId;
    }
}
