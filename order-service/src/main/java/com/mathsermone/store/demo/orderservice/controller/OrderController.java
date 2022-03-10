package com.mathsermone.store.demo.orderservice.controller;

import com.mathsermone.store.demo.orderservice.model.Order;
import com.mathsermone.store.demo.orderservice.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/orders")
public class OrderController {
    @Autowired
    OrderService orderService;

    @GetMapping
    public List<Order> getAll() {
        return orderService.getOrders();
    }
}
