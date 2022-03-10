package com.mathsermone.store.demo.shippingservice.service;

import com.mathsermone.store.demo.shippingservice.constants.OrderStatus;
import com.mathsermone.store.demo.shippingservice.model.Order;
import com.mathsermone.store.demo.shippingservice.model.Shipment;
import com.mathsermone.store.demo.shippingservice.repository.ShipmentRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;

@Slf4j
@Service
public class ShipmentService {
    @Autowired
    ShipmentRepository shipmentRepository;

    public Order handleOrder(Order order) {
        LocalDate shippingDate = null;
        if (LocalTime.now().isAfter(LocalTime.parse("10:00"))
                && LocalTime.now().isBefore(LocalTime.parse("18:00"))) {
            shippingDate = LocalDate.now().plusDays(1);
        } else {
            throw new RuntimeException("The current time is off the limits to place order.");
        }
        shipmentRepository.save(new Shipment()
                .setAddress(order.getShippingAddress())
                .setShippingDate(shippingDate));
        return order.setShippingDate(shippingDate)
                .setOrderStatus(OrderStatus.SUCCESS);
    }
}
