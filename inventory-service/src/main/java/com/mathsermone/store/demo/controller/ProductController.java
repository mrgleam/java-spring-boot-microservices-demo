package com.mathsermone.store.demo.controller;

import com.mathsermone.store.demo.model.Order;
import com.mathsermone.store.demo.model.Product;
import com.mathsermone.store.demo.service.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/products")
public class ProductController {
    @Autowired
    ProductService productService;

    @GetMapping
    public List<Product> getAllProducts() {
        log.info("Get all products invoked.");
        return productService.getProducts();
    }

    @PostMapping
    public Order processOrder(@RequestBody Order order) {
        return productService.handleOrder(order);
    }
}
