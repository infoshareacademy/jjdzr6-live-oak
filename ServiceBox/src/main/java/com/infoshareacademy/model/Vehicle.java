package com.infoshareacademy.model;

public class Vehicle {
    private int id;
    private String plateNumber;
    private String vin;
    private String manufacter;
    private String carName;
    private String carType;
    private String carPlatform;
    private String engineCapacity;
    private int productionYear;
    private String lasttechReview;
    private int odometerBefore = 0;
    private int odometerAfter = 0;

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

    @Override
    public String toString() {
        return "Vehicle{" +
                "id=" + id +
                ", plateNumber='" + plateNumber + '\'' +
                ", vin='" + vin + '\'' +
                ", manufacter='" + manufacter + '\'' +
                ", carName='" + carName + '\'' +
                ", carType='" + carType + '\'' +
                ", carPlatform='" + carPlatform + '\'' +
                ", engineCapacity='" + engineCapacity + '\'' +
                ", productionYear=" + productionYear +
                ", lasttechReview='" + lasttechReview + '\'' +
                ", odometerBefore=" + odometerBefore +
                ", odometerAfter=" + odometerAfter +
                '}';
    }

    public void setPlateNumber(String plateNumber) {
        this.plateNumber = plateNumber;
    }
}
