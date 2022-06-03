package com.infoshareacademy.repository;

import com.infoshareacademy.core.DatabaseInterface;
import com.infoshareacademy.model.Vehicle;

import java.util.ArrayList;

public class VehicleRepository {
    private final DatabaseInterface db;

    public VehicleRepository(DatabaseInterface db) {
        this.db = db;
    }

    public Vehicle findById(int id) throws Exception {
        for (Vehicle vehicle : db.getVehicles()) {
            if (id == vehicle.getId()) {
                return vehicle;
            }
        }

        throw new Exception("Nie znaleziono pojazdu o podanym numerze");
    }

    public Vehicle findByPlateNumber(String plateNumber) throws Exception {
        for (Vehicle vehicle : db.getVehicles()) {
            if (plateNumber.equals(vehicle.getPlateNumber())) {
                return vehicle;
            }
        }

        throw new Exception("Nie znaleziono pojazdu o podanym numerze rejestracyjnym");
    }

    public ArrayList<Vehicle> findAll() {
        return db.getVehicles();
    }

    public int getNextId() {
        return db.getVehicles().size() + 1;
    }
}
