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

    public Vehicle findByPlateNumber(String plateNumber) {
        // TODO
        return null;
    }

    public ArrayList<Task> findAll() {
        // TODO
        return null;
    }
}
