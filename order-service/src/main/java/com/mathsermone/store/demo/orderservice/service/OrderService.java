package com.mathsermone.store.demo.orderservice.service;

import com.mathsermone.store.demo.orderservice.constants.OrderStatus;
import com.mathsermone.store.demo.orderservice.model.Order;
import com.mathsermone.store.demo.orderservice.repository.OrderRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Slf4j
@Service
public class OrderService {
    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    OrderRepository orderRepository;

    @Value("${inventoryServiceUrl}")
    private String inventoryServiceUrl;

    @Value("${shipmentServiceUrl}")
    private String shipmentServiceUrl;


    public Order createOrder(Order order) {
        System.out.println(inventoryServiceUrl);
        boolean success = true;
        Order savedOrder = orderRepository.save(order);

        Order inventoryResponse = null;
        try {
            inventoryResponse = restTemplate.postForObject(inventoryServiceUrl, order, Order.class);
        } catch (Exception ex) {
            success = false;
        }

        Order shipmentResponse = null;
        try {
            shipmentResponse = restTemplate.postForObject(shipmentServiceUrl, order, Order.class);
        } catch (Exception ex) {
            success = false;
            HttpEntity<Order> deleteRequest = new HttpEntity<>(order);
            ResponseEntity<Order> deleteResponse = restTemplate.exchange(inventoryServiceUrl, HttpMethod.DELETE, deleteRequest, Order.class);
        }

        if (success) {
            savedOrder.setOrderStatus(OrderStatus.SUCCESS);
            savedOrder.setShippingDate(shipmentResponse.getShippingDate());
        } else {
            savedOrder.setOrderStatus(OrderStatus.FAILURE);
        }
        return orderRepository.save(savedOrder);
    }

    public List<Order> getOrders() {
        return orderRepository.findAll();
    }
}
