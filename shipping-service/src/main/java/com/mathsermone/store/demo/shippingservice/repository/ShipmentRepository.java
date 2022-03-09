package com.mathsermone.store.demo.shippingservice.repository;

import com.mathsermone.store.demo.shippingservice.model.Shipment;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ShipmentRepository extends MongoRepository<Shipment, ObjectId> {

}
