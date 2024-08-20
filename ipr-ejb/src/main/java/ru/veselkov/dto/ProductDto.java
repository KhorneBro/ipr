package ru.veselkov.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.StringJoiner;

public class ProductDto implements Serializable {
    private int id;
    private Integer userid;
    private String productType;
    private String productName;
    private long productPrice;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
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

    @Override
    public String toString() {
        return new StringJoiner(", ", ProductDto.class.getSimpleName() + "[", "]")
                .add("id=" + id)
                .add("userid=" + userid)
                .add("productType='" + productType + "'")
                .add("productName='" + productName + "'")
                .add("productPrice=" + productPrice)
                .toString();
    }
}
