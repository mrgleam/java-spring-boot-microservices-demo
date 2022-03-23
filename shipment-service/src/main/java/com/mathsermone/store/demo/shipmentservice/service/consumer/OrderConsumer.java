package com.mathsermone.store.demo.shipmentservice.service.consumer;

import com.mathsermone.store.demo.shipmentservice.constants.OrderStatus;
import com.mathsermone.store.demo.shipmentservice.model.Order;
import com.mathsermone.store.demo.shipmentservice.service.ShipmentService;
import com.mathsermone.store.demo.shipmentservice.service.producer.OrderProducer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Slf4j
@Service
public class OrderConsumer {

    @Autowired
    ShipmentService shipmentService;

    @Autowired
    OrderProducer orderProducer;

    @KafkaListener(topics = "orders", groupId = "shipping")
    public void consume(Order order) throws IOException {
        log.info("Order received to process: {}", order);
        if (OrderStatus.PREPARE_SHIPPING.equals(order.getOrderStatus())) {
            shipmentService.handleOrder(order)
                    .doOnSuccess(o -> {
                        log.info("Order processed successfully.");
                        orderProducer.sendMessage(order.setOrderStatus(OrderStatus.SHIPPING_SUCCESS));
                    })
                    .doOnError(e -> {
                        if (log.isErrorEnabled())
                            log.error("Order failed to process: " + e);
                        orderProducer.sendMessage(order.setOrderStatus(OrderStatus.SHIPPING_FAILURE)
                                .setResponseMessage(e.getMessage()));
                    })
                    .subscribe();
        }
    }
}
