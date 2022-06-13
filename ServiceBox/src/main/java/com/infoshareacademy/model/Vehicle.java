package com.infoshareacademy.model;

public class Vehicle {
    private int id;
    private String plateNumber;
    private String manufacter;
    private String carName;
    private String engineCapacity;
    private int productionYear;

    public Vehicle(int id, String plateNumber, String manufacter, String carName, String engineCapacity, int productionYear) {
        this.id = id;
        this.plateNumber = plateNumber;
        this.manufacter = manufacter;
        this.carName = carName;
        this.engineCapacity = engineCapacity;
        this.productionYear = productionYear;

    }

    public String getPlateNumber() {
        return plateNumber;
    }

    public int getId() {
        return id;
    }

    public void setPlateNumber(String plateNumber) {
        this.plateNumber = plateNumber;
    }

    @Override
    public String toString() {
        return "Vehicle{" +
                "id=" + id +
                ", plateNumber='" + plateNumber + '\'' +
                ", manufacter='" + manufacter + '\'' +
                ", carName='" + carName + '\'' +
                ", engineCapacity='" + engineCapacity + '\'' +
                ", productionYear=" + productionYear +
                '}';
    }
}
