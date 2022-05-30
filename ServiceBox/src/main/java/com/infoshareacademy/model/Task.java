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
}
