package com.mathsermone.store.demo.shippingservice.model;

import lombok.Data;

@Data
public class Address {
    private String name;
    private String house;
    private String street;
    private String city;
    private String zip;
}
