package com.infoshareacademy.core;

import com.infoshareacademy.model.Task;
import com.infoshareacademy.model.Vehicle;

import java.util.ArrayList;

public class MemoryDB implements DatabaseInterface{
    private static ArrayList<Task> tasks = new ArrayList<>();
    private static ArrayList<Vehicle> vehicles = new ArrayList<>();

    public ArrayList<Task> getTasks() {
        return tasks;
    }

    public void addTask(Task task) {
        tasks.add(task);
    }

    public ArrayList<Vehicle> getVehicles() {
        return vehicles;
    }

    public void addVehicle(Vehicle vehicle) {
        vehicles.add(vehicle);
    }
}
