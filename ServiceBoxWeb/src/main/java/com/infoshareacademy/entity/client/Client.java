package com.infoshareacademy.entity.client;

import com.infoshareacademy.entity.Entity;
import com.infoshareacademy.entity.client.Address;
import com.infoshareacademy.entity.vehicle.Vehicle;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Getter
@Setter
@EqualsAndHashCode(callSuper = false)
@ToString
public class Client extends Entity {
    // foreign key (one-to-one)
    private int userId;
    @NotBlank
    private String name;
    private Address address;
    @Pattern(regexp = "(^$)|[0-9]{10}")
    private String nip;
    @Pattern(regexp = "[0-9]{9}")
    @NotBlank
    private String phoneNumber;
    @Email
    @NotBlank
    private String email;
    private boolean allowNotify = false;

    private int vehicleId;

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