package com.shop.model;

import lombok.ToString;

@ToString
public class Order {
    private int id;
    private String productType;
    private double price;

    // --- ВРУЧНУЮ ДОБАВЛЕННЫЕ МЕТОДЫ ---

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getProductType() {
        return productType;
    }

    public void setProductType(String productType) {
        this.productType = productType;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}