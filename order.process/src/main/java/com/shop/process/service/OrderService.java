package com.shop.process.service;

import com.shop.model.Order;
import com.shop.storage.repository.OrderRepository;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class OrderService {
    private final OrderRepository repository;
    private final ExecutorService executorService;

    public OrderService(OrderRepository repository) {
        this.repository = repository;

        this.executorService = Executors.newFixedThreadPool(4);
    }

    public void processOrder(Order order) {
        executorService.submit(() -> {
            System.out.println("Processing order: " + order.getId() + " by " + Thread.currentThread().getName());

            try { Thread.sleep(1000); } catch (InterruptedException e) { e.printStackTrace(); }

            repository.save(order);
        });
    }

    public void stop() {
        executorService.shutdown();
    }
}