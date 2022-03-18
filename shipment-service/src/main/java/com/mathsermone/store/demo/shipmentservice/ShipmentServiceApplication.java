package com.mathsermone.store.demo.shipmentservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.ReactiveMongoDatabaseFactory;
import org.springframework.data.mongodb.ReactiveMongoTransactionManager;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@SpringBootApplication
public class ShipmentServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(ShipmentServiceApplication.class, args);
	}

	@Bean
	ReactiveMongoTransactionManager transactionManager(ReactiveMongoDatabaseFactory dbFactory) {
		return new ReactiveMongoTransactionManager(dbFactory);
	}

	@ControllerAdvice
	public class ExceptionController {
		@ExceptionHandler(value = RuntimeException.class)
		public ResponseEntity<Object> exception(RuntimeException exception) {
			return new ResponseEntity<>(exception.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
