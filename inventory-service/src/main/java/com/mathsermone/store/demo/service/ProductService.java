package com.mathsermone.store.demo.service;

import com.mathsermone.store.demo.constants.OrderStatus;
import com.mathsermone.store.demo.model.Order;
import com.mathsermone.store.demo.model.Product;
import com.mathsermone.store.demo.repository.ProductRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
public class ProductService {
    @Autowired
    ProductRepository productRepository;

    public List<Product> getProducts() {
        return productRepository.findAll();
    }

    @Transactional
    public Order handleOrder(Order order) {
        order.getLineItems()
                .forEach((l -> {
                    Product p = productRepository.findById(l.getProductId())
                            .orElseThrow(() -> new RuntimeException("Could not find the product: " + l.getProductId()));
                    if (p.getStock() >= l.getQuantity()) {
                        p.setStock(p.getStock() - l.getQuantity());
                        productRepository.save(p);
                    } else {
                        throw new RuntimeException("Product is out of stock: " + l.getProductId());
                    }
                }));
        return order.setOrderStatus(OrderStatus.SUCCESS);
    }

    @Transactional
    public Order revertOrder(Order order) {
        order.getLineItems()
                .forEach(l -> {
                    Product p = productRepository.findById(l.getProductId())
                            .orElseThrow(() -> new RuntimeException("Could not find the product: " + l.getProductId()));
                    p.setStock(p.getStock() + l.getQuantity());
                    productRepository.save(p);
                });
        return order.setOrderStatus(OrderStatus.SUCCESS);
    }
}
