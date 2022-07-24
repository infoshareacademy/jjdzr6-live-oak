package com.infoshareacademy.entity;

import lombok.Data;

@Data
public class Address {
    private int addressId;
    private String street;
    private String houseNumber;
    private String flatNumber;
    private String zipCode;
    private String city;
}
