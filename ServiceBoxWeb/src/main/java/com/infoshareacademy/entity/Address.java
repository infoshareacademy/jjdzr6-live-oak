package com.infoshareacademy.entity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Address {
    private String street;
    private String houseNumber;
    private String flatNumber;
    private String zipCode;
    private String city;

    public Address() {
    }

    public Address(String street, String houseNumber, String flatNumber, String zipCode, String city) {
        this.street = street;
        this.houseNumber = houseNumber;
        this.flatNumber =  flatNumber;
        this.zipCode = zipCode;
        this.city = city;
    }

    @Override
    public String toString() {
        return street + " " + houseNumber  + "/" + flatNumber + ", " + zipCode + " " + city;
    }
}
