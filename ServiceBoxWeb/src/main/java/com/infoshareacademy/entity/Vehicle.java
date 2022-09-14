package com.infoshareacademy.entity;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.*;

@Getter
@Setter
@EqualsAndHashCode(callSuper = false)
@ToString
public class Vehicle extends Entity {

    private int vehicleId;
    @NotBlank
    private String make;
    @NotBlank
    private String model;
    @NotBlank
    private String plateNumber;
    @NotNull
    private double engineCapacity;
    @NotNull
//    @Size(min=1950, max=2022)
    private int productionYear;
    @NotNull
//    @Size(max=999999)
    private int mileage;
    @Pattern(regexp = "[A-Z0-9]{17}]")
    private String vin;

    // foreign key (many-to-one)
    private int clientId;

    public Vehicle() {
    }

    public Vehicle( String make, String model, String plateNumber, double engineCapacity, int productionYear) {
        this.make = make;
        this.model = model;
        this.plateNumber = plateNumber;
        this.engineCapacity = engineCapacity;
        this.productionYear = productionYear;
    }
}
