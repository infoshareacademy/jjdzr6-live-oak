package com.infoshareacademy.entity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Client extends Entity {
    // foreign key (one-to-one)
    private int userId;
    private String name;
    private Address address;
    private String nip;
    private String phoneNumber;
    private String email;
    private boolean allowNotify = false;

    public Client() {
    }

    public Client(String name, String phoneNumber, String email) {
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.email = email;
    }

    /**
     * To add a new car we must set client id in vehicle object,
     * relation "one to many" - one client has many cars
     *
     * @param vehicle
     */
    public void addVehicle(Vehicle vehicle) {
        vehicle.setClientId(getId());
    }
}
