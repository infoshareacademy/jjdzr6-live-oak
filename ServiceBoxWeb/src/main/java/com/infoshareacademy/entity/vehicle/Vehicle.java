package com.infoshareacademy.entity.vehicle;

import com.infoshareacademy.entity.client.Client;
import com.infoshareacademy.entity.serviceorder.ServiceOrder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "vehicle")
@Getter
@Setter
@NoArgsConstructor
public class Vehicle {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "make", nullable = false)
    private String make;

    @Column(name = "model", nullable = false)
    private String model;

    @Column(name = "plate_number", nullable = false, unique = true)
    private String plateNumber;

    @Column(name = "engine_capacity")
    private Double engineCapacity;

    @Column(name = "production_year")
    private Integer productionYear;

    @Column(name = "mileage")
    private Integer mileage;

    @Column(name = "vin", columnDefinition = "varchar(17)")
    private String vin;

    @OneToMany(mappedBy="vehicle", fetch = FetchType.LAZY)
    private List<ServiceOrder> serviceOrders;

    @ManyToOne(optional = false)
    @JoinColumn(name = "client_id")
    private Client client;
}