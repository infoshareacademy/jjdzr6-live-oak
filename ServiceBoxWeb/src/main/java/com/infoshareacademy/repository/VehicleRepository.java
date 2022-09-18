package com.infoshareacademy.repository;

import com.fasterxml.jackson.core.type.TypeReference;
import com.infoshareacademy.entity.client.Client;
import com.infoshareacademy.entity.vehicle.Vehicle;

import java.util.ArrayList;
import java.util.List;

public class VehicleRepository extends Repository<Vehicle> {
    public VehicleRepository(String filename, TypeReference<ArrayList<Vehicle>> typeReference) {
        super(filename, typeReference);
    }

    public Vehicle findByPlateNumber(String plateNumber) {
        return objects.stream()
                .filter(e -> e.getPlateNumber().equalsIgnoreCase(plateNumber))
                .findFirst()
                .orElse(null);
    }

    public List<Vehicle> findClientVehicles(Client client) {
        return objects.stream()
                .filter(e -> e.getClientId() == client.getId())
                .toList();
    }

    public List<Vehicle> findBy(String search) {
        return objects.stream()
                .filter(e -> e.toString().toLowerCase().contains(search.toLowerCase()))
                .toList();
    }
}