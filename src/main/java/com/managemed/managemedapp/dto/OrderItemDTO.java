package com.managemed.managemedapp.dto;

import java.time.LocalDate;

public class OrderItemDTO {

    private String product;
    private int quantity;
    private int price;
    private LocalDate orderDate;

    public OrderItemDTO(String product, int quantity, int price, LocalDate orderDate) {
        this.product = product;
        this.quantity = quantity;
        this.price = price;
        this.orderDate = orderDate;
    }

    public String getProduct() { return product; }
    public int getQuantity() { return quantity; }
    public int getPrice() { return price; }
    public LocalDate getOrderDate() { return orderDate; }
}
