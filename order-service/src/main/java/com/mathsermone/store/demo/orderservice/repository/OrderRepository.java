package com.mathsermone.store.demo.orderservice.repository;

import com.mathsermone.store.demo.orderservice.model.Order;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface OrderRepository extends MongoRepository<Order, ObjectId> {

}
