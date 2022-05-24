package com.infoshareacademy.model;

public class Vehicle {
    private final String plateNumber;
    private String vin;
    private String manufacter;
    private String carName;
    private String carType;
    private String carPlatform;
    private float engineCapacity;
    private int productionYear;
    private String lasttechReview;
    private int odometerBefore;
    private int odometerAfter;

    public Vehicle(String plateNumber) {
        this.plateNumber = plateNumber;
    }

    @Override
    public String toString() {
        return "Vehicle{" +
                "plateNumber='" + plateNumber + '\'' +
                '}';
    }
}
