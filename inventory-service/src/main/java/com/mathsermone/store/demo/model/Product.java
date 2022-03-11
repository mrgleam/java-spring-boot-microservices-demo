package com.mathsermone.store.demo.model;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.mathsermone.store.demo.serdeser.ObjectIdSerializer;
import lombok.Data;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document
public class Product {

    @Id
    @JsonSerialize(using = ObjectIdSerializer.class)
    private ObjectId id;
    private String name;
    private Long price;
    private Integer stock;
}
