package com.mathsermone.store.demo.repository;

import com.mathsermone.store.demo.model.Product;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface ProductRepository extends ReactiveMongoRepository<Product, ObjectId> {

}
