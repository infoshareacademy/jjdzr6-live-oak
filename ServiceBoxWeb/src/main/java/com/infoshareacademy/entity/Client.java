package com.infoshareacademy.entity;

import lombok.Data;

import java.util.HashSet;
import java.util.Set;

@Data
public class Client {
    private int id;
    private User user;
    private String name;
    private Address address;
    private String nip;
    private String phoneNumber;
    private String email;
    private boolean allowNotify = false;
    private Set<Vehicle> vehicles = new HashSet<>();

    public void addVehicle(Vehicle vehicle) {
        vehicles.add(vehicle);
    }
}
