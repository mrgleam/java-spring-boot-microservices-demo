package com.mathsermone.store.demo.orderservice.controller;

import com.mathsermone.store.demo.orderservice.constants.OrderStatus;
import com.mathsermone.store.demo.orderservice.model.Order;
import com.mathsermone.store.demo.orderservice.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/orders")
public class OrderController {
    @Autowired
    OrderService orderService;

    @PostMapping
    public Order create(@RequestBody Order order) {
        Order processedOrder = orderService.createOrder(order);
        if (OrderStatus.FAILURE.equals(processedOrder.getOrderStatus())) {
            throw new RuntimeException("Order processing failed, please try again later.");
        }
        return processedOrder;
    }

    @GetMapping
    public List<Order> getAll() {
        return orderService.getOrders();
    }
}
