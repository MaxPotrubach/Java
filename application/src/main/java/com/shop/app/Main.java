package com.shop.app;

import com.github.javafaker.Faker;
import com.shop.model.Order;
import com.shop.process.service.OrderService;
import com.shop.storage.repository.OrderRepository;

public class Main {
    public static void main(String[] args) {
        System.out.println("=== SYSTEM START ===");

        OrderRepository repository = new OrderRepository();

        OrderService service = new OrderService(repository);

        Faker faker = new Faker();

        for (int i = 0; i < 10; i++) {
            Order order = new Order();
            order.setId(i + 1);
            order.setProductType(faker.commerce().productName());
            String priceStr = faker.commerce().price().replace(",", ".");
            order.setPrice(Double.parseDouble(priceStr));

            System.out.println("Created new order: " + order.getProductType());

            service.processOrder(order);
        }

        service.stop();

        System.out.println("=== All tasks submitted ===");
    }
}