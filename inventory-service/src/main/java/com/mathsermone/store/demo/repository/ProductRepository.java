package com.mathsermone.store.demo.repository;

import com.mathsermone.store.demo.model.Product;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ProductRepository extends MongoRepository<Product, ObjectId> {

}
