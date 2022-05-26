package com.infoshareacademy.model;

public class Vehicle {
    private String plateNumber;
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

    public Vehicle(String plateNumber, String manufacter, String carName, float engineCapacity, int productionYear) {
        this.plateNumber = plateNumber;
        this.manufacter = manufacter;
        this.carName = carName;
        this.engineCapacity = engineCapacity;
        this.productionYear = productionYear;
    }

    @Override
    public String toString() {
        return "Vehicle{" +
                "plateNumber='" + plateNumber + '\'' +
                '}';
    }
}
