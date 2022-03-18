package com.mathsermone.store.demo.orderservice.repository;

import com.mathsermone.store.demo.orderservice.model.Order;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface OrderRepository extends ReactiveMongoRepository<Order, ObjectId> {

}
