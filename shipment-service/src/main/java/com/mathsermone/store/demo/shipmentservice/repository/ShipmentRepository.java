package com.mathsermone.store.demo.shipmentservice.repository;

import com.mathsermone.store.demo.shipmentservice.model.Shipment;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ShipmentRepository extends MongoRepository<Shipment, ObjectId> {

}
