package com.shop.app;

import com.github.javafaker.Faker;
import com.shop.model.Clothing;
import com.shop.model.Electronics;
import com.shop.model.Order;
import com.shop.process.service.OrderService;
import com.shop.storage.repository.OrderRepository;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        System.out.println("=== SYSTEM START ===");

        OrderRepository repository = new OrderRepository();
        OrderService service = new OrderService(repository);
        Faker faker = new Faker();

        List<Order<Electronics>> electronicsOrders = new ArrayList<>();

        for (int i = 0; i < 5; i++) {
            Electronics phone = Electronics.builder()
                    .name(faker.commerce().productName())
                    .price(Double.parseDouble(faker.commerce().price().replace(",", ".")))
                    .warrantyPeriod(24)
                    .build();

            Order<Electronics> order = new Order<>();
            order.setId(i + 1);
            order.setProduct(phone);
            order.setComment("Fast delivery");

            electronicsOrders.add(order);
        }

        System.out.println("--- Submitting Electronics via Method Reference ---");

        electronicsOrders.forEach(service::processOrder);

        Clothing shirt = Clothing.builder()
                .name(faker.commerce().material() + " Shirt")
                .price(50.0)
                .size("L")
                .build();

        Order<Clothing> clothingOrder = new Order<>();
        clothingOrder.setId(99);
        clothingOrder.setProduct(shirt);

        System.out.println("--- Submitting Clothing manually ---");
        service.processOrder(clothingOrder);

        service.stop();
        System.out.println("=== All tasks submitted ===");
    }
}