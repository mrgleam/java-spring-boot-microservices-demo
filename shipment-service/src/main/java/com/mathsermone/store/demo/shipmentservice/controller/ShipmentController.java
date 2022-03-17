package com.mathsermone.store.demo.shipmentservice.controller;

import com.mathsermone.store.demo.shipmentservice.model.Order;
import com.mathsermone.store.demo.shipmentservice.service.ShipmentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@Slf4j
@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/shipments")
public class ShipmentController {
    @Autowired
    ShipmentService shipmentService;

    @PostMapping
    public Mono<Order> process(@RequestBody Order order) {
        return shipmentService.handleOrder(order);
    }
}
