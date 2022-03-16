package com.mathsermone.store.demo.service;

import com.mathsermone.store.demo.constants.OrderStatus;
import com.mathsermone.store.demo.model.Order;
import com.mathsermone.store.demo.model.Product;
import com.mathsermone.store.demo.repository.ProductRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Slf4j
@Service
public class ProductService {
    @Autowired
    ProductRepository productRepository;

    public Flux<Product> getProducts() {
        return productRepository.findAll();
    }

    @Transactional
    public Mono<Order> handleOrder(Order order) {
        log.info("Handle order invoked with: {}", order);
        return Flux.fromIterable(order.getLineItems())
                .flatMap(l -> productRepository.findById(l.getProductId()))
                .flatMap(p -> {
                    int q = order.getLineItems()
                            .stream()
                            .filter(l -> l.getProductId()
                                    .equals(p.getId()))
                            .findAny()
                            .get()
                            .getQuantity();
                    if (p.getStock() >= q) {
                        p.setStock(p.getStock() - q);
                        return productRepository.save(p);
                    } else {
                        return Mono.error(new RuntimeException("Product is out of stock: " + p.getId()));
                    }
                })
                .then(Mono.just(order.setOrderStatus(OrderStatus.SUCCESS)));
    }

    @Transactional
    public Mono<Order> revertOrder(Order order) {
        return Flux.fromIterable(order.getLineItems())
                .flatMap(l -> productRepository.findById(l.getProductId()))
                .flatMap(p -> {
                    int q = order.getLineItems().stream()
                            .filter(l -> l.getProductId().equals(p.getId()))
                            .findAny().get()
                            .getQuantity();
                    p.setStock(p.getStock() + q);
                    return productRepository.save(p);
                }).then(Mono.just(order.setOrderStatus(OrderStatus.SUCCESS)));
    }
}
