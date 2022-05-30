package com.infoshareacademy.repository;

import com.infoshareacademy.core.DatabaseInterface;
import com.infoshareacademy.model.Task;
import com.infoshareacademy.model.Vehicle;

import java.util.ArrayList;

public class VehicleRepository {
    private DatabaseInterface db;

    public VehicleRepository(DatabaseInterface db) {
        this.db = db;
    }

    public Vehicle findByPlateNumber(String plateNumber) throws Exception {

        for (Vehicle vehicle : db.getVehicles()) {
            if (plateNumber.equals(vehicle.getPlateNumber())) {
                return vehicle;
            }
        }
        throw new Exception("Nie znaleziono pojazdu o podanym numerze");

    }

    public ArrayList<Task> findAll() {
        // TODO
        return null;
    }
}
