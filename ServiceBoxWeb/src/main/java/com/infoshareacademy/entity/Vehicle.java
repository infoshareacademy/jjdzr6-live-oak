package com.infoshareacademy.entity;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Getter
@Setter
@EqualsAndHashCode
@ToString
public class Vehicle extends Entity {
    @NotBlank
    private String make;
    @NotBlank
    private String model;
    @NotBlank
    private String plateNumber;
    @NotBlank
    private double engineCapacity;
    @NotBlank
    @Size(min=1950, max=2022)
    private int productionYear;
    @NotNull
    @Size(max=999999)
    private int mileage;
    @Pattern(regexp = "[A-Z0-9]{17}]")
    private String vin;

    // foreign key (many-to-one)
    private int clientId;

    public Vehicle() {
    }

    public Vehicle(String make, String model, String plateNumber, double engineCapacity, int productionYear) {
        this.make = make;
        this.model = model;
        this.plateNumber = plateNumber;
        this.engineCapacity = engineCapacity;
        this.productionYear = productionYear;
    }
}
