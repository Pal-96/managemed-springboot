package com.managemed.managemedapp.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.stereotype.Component;

// import javax.persistence.Entity;

@Component
@Entity
@Table(name = "STOCK")
public class Product {
    @Id
    @Column(name = "PRODUCT")
    private String product;

    @Column(name = "QUANTITY")
    private int quantity;

    @Column(name = "UNITPRICE")
    private int unitprice;

    @Column(name = "DESCRIPTION")
    private String description;

    public String getProduct() {
        return product;
    }

    public void setProduct(String product) {
        this.product = product;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getUnitprice() {
        return unitprice;
    }

    public void setUnitprice(int unitprice) {
        this.unitprice = unitprice;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
