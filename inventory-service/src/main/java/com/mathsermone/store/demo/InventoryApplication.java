package com.mathsermone.store.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.ReactiveMongoDatabaseFactory;
import org.springframework.data.mongodb.ReactiveMongoTransactionManager;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;


@SpringBootApplication
public class InventoryApplication {

	public static void main(String[] args) {
		SpringApplication app = new SpringApplication(InventoryApplication.class);
		app.setWebApplicationType(WebApplicationType.REACTIVE);
		SpringApplication.run(InventoryApplication.class, args);
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
