package com.infoshareacademy.entity;

import lombok.*;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@EqualsAndHashCode(callSuper = false)
@ToString
@NoArgsConstructor
public class Vehicle extends Entity {
    @NotBlank
    private String make;
    @NotBlank
    private String model;
    @NotBlank
    private String plateNumber;
    private double engineCapacity;
    private int productionYear;
    private int mileage;
    private String vin;
    // foreign key (many-to-one)
    private int clientId;

    public Vehicle(String make, String model, String plateNumber, double engineCapacity, int productionYear) {
        this.make = make;
        this.model = model;
        this.plateNumber = plateNumber;
        this.engineCapacity = engineCapacity;
        this.productionYear = productionYear;
    }
}