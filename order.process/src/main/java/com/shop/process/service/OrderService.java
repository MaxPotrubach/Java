package com.shop.process.service;

import com.shop.model.Order;
import com.shop.model.Product;
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

    public <T extends Product> void processOrder(Order<T> order) {
        executorService.submit(() -> {
            String threadName = Thread.currentThread().getName();

            System.out.println("Processing order ID: " + order.getId()
                    + " [" + order.getProduct().getName() + "] by " + threadName);

            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                e.printStackTrace();
            }

            repository.save(order);
        });
    }

    public void stop() {
        executorService.shutdown();
    }
}