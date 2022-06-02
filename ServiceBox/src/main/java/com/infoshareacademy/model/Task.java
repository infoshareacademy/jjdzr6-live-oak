package com.infoshareacademy.model;

import java.time.LocalDate;
import java.util.ArrayList;

public class Task {
    private final int id;
    private String clientName;
    private Vehicle vehicle;
    private String repairDescription;
    private ArrayList<String> thingsToDo;
    private ArrayList<String> repairsPerformed;
    private int initialCost;
    private int repairCost;
    private LocalDate dateAcceptRepair;
    private LocalDate plannedRepair;
    private LocalDate dateOfRepair;
    private LocalDate pickingUp;

    public Task(int id, String clientName, Vehicle vehicle, String repairDescription) {
        this.id = id;
        this.clientName = clientName;
        this.vehicle = vehicle;
        this.repairDescription = repairDescription;

        dateAcceptRepair = LocalDate.now();
        thingsToDo = new ArrayList<>();
        repairsPerformed = new ArrayList<>();
    }

    public int getId() {
        return id;
    }

    public void addThingToDo(String description) {
        thingsToDo.add(description);
    }

    public void addPerformedRepair(String description) {
        repairsPerformed.add(description);
    }

    @Override
    public String toString() {
        return "Task{" +
                "id=" + id +
                ", clientName='" + clientName + '\'' +
                ", vehicle=" + vehicle +
                ", repairDescription='" + repairDescription + '\'' +
                ", thingsToDo=" + thingsToDo +
                ", repairsPerformed=" + repairsPerformed +
                ", dateAcceptRepair=" + dateAcceptRepair +
                '}';
    }

    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }

    public String getRepairDescription() {
        return repairDescription;
    }

    public void setRepairDescription(String repairDescription) {
        this.repairDescription = repairDescription;
    }

    public int getInitialCost() {
        return initialCost;
    }

    public void setInitialCost(int initialCost) {
        this.initialCost = initialCost;
    }

    public int getRepairCost() {
        return repairCost;
    }

    public void setRepairCost(int repairCost) {
        this.repairCost = repairCost;
    }

    public LocalDate getDateAcceptRepair() {
        return dateAcceptRepair;
    }

    public void setDateAcceptRepair(LocalDate dateAcceptRepair) {
        this.dateAcceptRepair = dateAcceptRepair;
    }

    public LocalDate getPlannedRepair() {
        return plannedRepair;
    }

    public void setPlannedRepair(LocalDate plannedRepair) {
        this.plannedRepair = plannedRepair;
    }

    public LocalDate getDateOfRepair() {
        return dateOfRepair;
    }

    public void setDateOfRepair(LocalDate dateOfRepair) {
        this.dateOfRepair = dateOfRepair;
    }

    public LocalDate getPickingUp() {
        return pickingUp;
    }

    public void setPickingUp(LocalDate pickingUp) {
        this.pickingUp = pickingUp;
    }
}
