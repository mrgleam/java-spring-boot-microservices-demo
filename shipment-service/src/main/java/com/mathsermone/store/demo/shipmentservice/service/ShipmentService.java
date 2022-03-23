package com.mathsermone.store.demo.shipmentservice.service;

import com.mathsermone.store.demo.shipmentservice.constants.OrderStatus;
import com.mathsermone.store.demo.shipmentservice.model.Order;
import com.mathsermone.store.demo.shipmentservice.repository.ShipmentRepository;
import com.mathsermone.store.demo.shipmentservice.model.Shipment;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.time.LocalDate;
import java.time.LocalTime;

@Slf4j
@Service
public class ShipmentService {
    @Autowired
    ShipmentRepository shipmentRepository;

    public Mono<Order> handleOrder(Order order) {
        return Mono.just(order)
                .flatMap(o -> {
                    LocalDate shippingDate;
                    if (LocalTime.now().isAfter(LocalTime.parse("00:00"))
                            && LocalTime.now().isBefore(LocalTime.parse("23:59"))) {
                        shippingDate = LocalDate.now().plusDays(1);
                    } else {
                        return Mono.error(new RuntimeException("The current time is off the limits to place order."));
                    }
                    return shipmentRepository.save(new Shipment()
                            .setAddress(order.getShippingAddress())
                            .setShippingDate(shippingDate));
                })
                .map(s -> order.setShippingDate(s.getShippingDate()).setOrderStatus(OrderStatus.SUCCESS));
    }
}
