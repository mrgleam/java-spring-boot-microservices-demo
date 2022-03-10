package com.mathsermone.store.demo.shippingservice.controller;

import com.mathsermone.store.demo.shippingservice.model.Order;
import com.mathsermone.store.demo.shippingservice.service.ShipmentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/shipments")
public class ShipmentController {
    @Autowired
    ShipmentService shipmentService;

    @PostMapping
    public Order process(@RequestBody Order order) {
        return shipmentService.handleOrder(order);
    }
}
