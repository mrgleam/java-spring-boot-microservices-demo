package com.mathsermone.store.demo.orderservice.service;

import com.mathsermone.store.demo.orderservice.constants.OrderStatus;
import com.mathsermone.store.demo.orderservice.model.Order;
import com.mathsermone.store.demo.orderservice.repository.OrderRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Slf4j
@Service
public class OrderService {
    @Autowired
    private WebClient webClient;

    @Autowired
    OrderRepository orderRepository;

    @Value("${inventoryServiceUrl}")
    private String inventoryServiceUrl;

    @Value("${shipmentServiceUrl}")
    private String shipmentServiceUrl;

    public Mono<Order> createOrder(Order order) {
        return Mono.just(order)
                .flatMap(orderRepository::save)
                .flatMap(o -> webClient.method(HttpMethod.POST)
                            .uri(inventoryServiceUrl)
                            .accept(MediaType.APPLICATION_JSON)
                            .body(BodyInserters.fromValue(o))
                            .exchangeToMono(response -> response.bodyToMono(Order.class)))
                .onErrorResume(err -> Mono.just(order.setOrderStatus(OrderStatus.FAILURE)
                            .setResponseMessage(err.getMessage())))
                .flatMap(o -> {
                    if (!OrderStatus.FAILURE.equals(o.getOrderStatus())) {
                        return webClient.method(HttpMethod.POST)
                                .uri(shipmentServiceUrl)
                                .accept(MediaType.APPLICATION_JSON)
                                .body(BodyInserters.fromValue(o))
                                .exchangeToMono(response -> response.bodyToMono(Order.class));
                    } else {
                        return Mono.just(o);
                    }
                })
                .onErrorResume(err -> webClient.method(HttpMethod.DELETE)
                            .uri(inventoryServiceUrl)
                            .accept(MediaType.APPLICATION_JSON)
                            .body(BodyInserters.fromValue(order))
                            .retrieve()
                            .bodyToMono(Order.class)
                            .map(o -> o.setOrderStatus(OrderStatus.FAILURE)
                                    .setResponseMessage(err.getMessage())))
                .map(o -> {
                    if (!OrderStatus.FAILURE.equals(o.getOrderStatus())) {
                        return order.setShippingDate(o.getShippingDate())
                                .setOrderStatus(OrderStatus.SUCCESS);
                    } else {
                        return order.setOrderStatus(OrderStatus.FAILURE)
                                .setResponseMessage(o.getResponseMessage());
                    }
                })
                .flatMap(orderRepository::save);
    }

    public Flux<Order> getOrders() {
        return orderRepository.findAll();
    }
}
