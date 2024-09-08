package ru.veselkov.model;

import jakarta.persistence.*;
import ru.veselkov.model.enums.Role;

import java.io.Serializable;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.StringJoiner;

@Entity
@Table(name = "customer_info", schema = "public", catalog = "postgres")
public class Customer implements Serializable {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "customer_id")
    private int customerId;
    @Basic
    @Column(name = "username")
    private String username;
    @Basic
    @Column(name = "firstname")
    private String firstname;
    @Basic
    @Column(name = "surname")
    private String surname;
    @Basic
    @Column(name = "patronymic")
    private String patronymic;
    @Basic
    @Column(name = "user_role")
    private Role userRole;
    @Basic
    @Column(name = "create_date")
    private Date createDate;
    @Basic
    @Column(name = "is_delete")
    private boolean isDelete;
    @Basic
    @Column(name = "transaction")
    private String transaction;
    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Product> productsByCustomerId = new ArrayList<>();

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
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

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public boolean isDelete() {
        return isDelete;
    }

    public void setDelete(boolean delete) {
        isDelete = delete;
    }

    public String getTransaction() {
        return transaction;
    }

    public void setTransaction(String transaction) {
        this.transaction = transaction;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Customer customer = (Customer) o;

        if (customerId != customer.customerId) return false;
        if (isDelete != customer.isDelete) return false;
        if (username != null ? !username.equals(customer.username) : customer.username != null) return false;
        if (firstname != null ? !firstname.equals(customer.firstname) : customer.firstname != null) return false;
        if (surname != null ? !surname.equals(customer.surname) : customer.surname != null) return false;
        if (patronymic != null ? !patronymic.equals(customer.patronymic) : customer.patronymic != null) return false;
        if (userRole != null ? !userRole.equals(customer.userRole) : customer.userRole != null) return false;
        if (createDate != null ? !createDate.equals(customer.createDate) : customer.createDate != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = customerId;
        result = 31 * result + (username != null ? username.hashCode() : 0);
        result = 31 * result + (firstname != null ? firstname.hashCode() : 0);
        result = 31 * result + (surname != null ? surname.hashCode() : 0);
        result = 31 * result + (patronymic != null ? patronymic.hashCode() : 0);
        result = 31 * result + (userRole != null ? userRole.hashCode() : 0);
        result = 31 * result + (createDate != null ? createDate.hashCode() : 0);
        result = 31 * result + (isDelete ? 1 : 0);
        return result;
    }

    public List<Product> getProductsByCustomerId() {
        return productsByCustomerId;
    }

    public void setProductsByCustomerId(List<Product> productsByCustomerId) {
        this.productsByCustomerId = productsByCustomerId;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", Customer.class.getSimpleName() + "[", "]")
                .add("customerId=" + customerId)
                .add("username='" + username + "'")
                .add("firstname='" + firstname + "'")
                .add("surname='" + surname + "'")
                .add("patronymic='" + patronymic + "'")
                .add("userRole=" + userRole)
                .add("createDate=" + createDate)
                .add("isDelete=" + isDelete)
                .add("Products = '" + productsByCustomerId == null || productsByCustomerId.isEmpty() ? "" : productsByCustomerId + "'")
                .toString();
    }
}
