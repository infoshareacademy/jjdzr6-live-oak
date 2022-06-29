package com.infoshareacademy.model;

import com.infoshareacademy.core.DatabaseInterface;
import com.infoshareacademy.core.EntityManager;
import com.infoshareacademy.core.JsonFileDatabase;
import com.infoshareacademy.repository.VehicleRepository;

import java.time.LocalDate;
import java.util.ArrayList;


public class Task {
    private final int id;
    private String clientName;
    private int vehicleId;
    private String repairDescription;
    private ArrayList<String> thingsToDo;
    private ArrayList<String> repairsPerformed;
    private LocalDate dateAcceptRepair;

    public Task(int id, String clientName, Vehicle vehicle, String repairDescription) {
        this.id = id;
        this.clientName = clientName;
        this.vehicleId = vehicle.getId();
        this.repairDescription = repairDescription;

        dateAcceptRepair = LocalDate.now();
        thingsToDo = new ArrayList<>();
        repairsPerformed = new ArrayList<>();
    }

    public int getId() {
        return id;
    }

    public Vehicle getVehicle() {
        try {
            return EntityManager.getInstance().getVehicleRepository().findById(vehicleId);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    public void setRepairDescription(String repairDescription) {
        this.repairDescription = repairDescription;
    }

    public String getClientName() {
        return clientName;
    }

    @Override
    public String toString() {
        return "Task{" +
                "id=" + id +
                ", clientName='" + clientName + '\'' +
                ", vehicle=" + getVehicle() +
                ", repairDescription='" + repairDescription + '\'' +
                '}';
    }
}
