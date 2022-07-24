package com.infoshareacademy.entity;

import lombok.Data;

@Data
public class Vehicle {
    private int vehicleId;
    private String make;
    private String model;
    private String plateNumber;
    private float engineCapacity;
    private int productionYear;
    private int mileage;
    private String vin;
}
