package ru.veselkov.model;

import jakarta.persistence.*;

import java.util.List;
import java.util.StringJoiner;

@Entity
public class Product {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "product_id")
    private int productId;
    @Basic
    @Column(name = "product_type")
    private String productType;
    @Basic
    @Column(name = "product_name")
    private String productName;
    @Basic
    @Column(name = "product_price")
    private long productPrice;
    @Basic
    @Column(name = "is_delete")
    private boolean isDelete;
//
//    @Basic
//    @Column(name = "customerId")
//    private int customerId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id")
    private Customer customer;

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public String getProductType() {
        return productType;
    }

    public void setProductType(String productType) {
        this.productType = productType;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public long getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(long productPrice) {
        this.productPrice = productPrice;
    }

    public boolean isDelete() {
        return isDelete;
    }

    public void setDelete(boolean delete) {
        isDelete = delete;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Product product = (Product) o;

        if (productId != product.productId) return false;
        if (productPrice != product.productPrice) return false;
        if (isDelete != product.isDelete) return false;
        if (productType != null ? !productType.equals(product.productType) : product.productType != null) return false;
        if (productName != null ? !productName.equals(product.productName) : product.productName != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = productId;
        result = 31 * result + (productType != null ? productType.hashCode() : 0);
        result = 31 * result + (productName != null ? productName.hashCode() : 0);
        result = 31 * result + (int) (productPrice ^ (productPrice >>> 32));
        result = 31 * result + (isDelete ? 1 : 0);
        return result;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", Product.class.getSimpleName() + "[", "]")
                .add("productId=" + productId)
                .add("productType='" + productType + "'")
                .add("productName='" + productName + "'")
                .add("productPrice=" + productPrice)
                .add("isDelete=" + isDelete)
                .toString();
    }
}
