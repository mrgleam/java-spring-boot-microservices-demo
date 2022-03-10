package com.mathsermone.store.demo.orderservice.service;

import com.mathsermone.store.demo.orderservice.model.Order;
import com.mathsermone.store.demo.orderservice.repository.OrderRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class OrderService {
    @Autowired
    OrderRepository orderRepository;

    public List<Order> getOrders() {
        return orderRepository.findAll();
    }
}
