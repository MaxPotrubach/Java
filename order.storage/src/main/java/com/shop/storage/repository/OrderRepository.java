package com.shop.storage.repository;

import com.shop.model.Order;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class OrderRepository {

    private final List<Order> orders = Collections.synchronizedList(new ArrayList<>());

    public void save(Order order) {
        orders.add(order);
        System.out.println("[Storage] Order saved: " + order);
    }

    public List<Order> getAll() {
        return new ArrayList<>(orders);
    }
}