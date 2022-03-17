package com.mathsermone.store.demo.shipmentservice.repository;

import com.mathsermone.store.demo.shipmentservice.model.Shipment;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface ShipmentRepository extends ReactiveMongoRepository<Shipment, ObjectId> {

}
