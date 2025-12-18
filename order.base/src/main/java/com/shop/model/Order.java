package com.shop.model;

import lombok.Data;

@Data
public class Order<T extends Product> {
    private int id;
    private T product;
    private String comment;
}