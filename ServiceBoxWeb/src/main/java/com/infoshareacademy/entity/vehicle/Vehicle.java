package com.infoshareacademy.entity.vehicle;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "vehicle")
@Getter
@Setter
@NoArgsConstructor
public class Vehicle {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "make", nullable = false)
    private String make;
    @Column(name = "model", nullable = false)
    private String model;
    @Column(name = "plate_number", nullable = false, unique = true)
    private String plateNumber;
    @Column(name = "engine_capacity")
    private double engineCapacity;
    @Column(name = "production_year")
    private int productionYear;
    @Column(name = "mileage")
    private int mileage;
    @Column(name = "vin", columnDefinition = "VARCHAR(17)")
    private String vin;
}