package com.infoshareacademy.core;

import com.infoshareacademy.model.Task;
import com.infoshareacademy.model.Vehicle;

import java.util.ArrayList;

public class MemoryDB {
    private static ArrayList<Task> tasks = new ArrayList<>();
    private static ArrayList<Vehicle> vehicles = new ArrayList<>();

    public static ArrayList<Task> getTasks() {
        return tasks;
    }

    public static void addTask(Task task) {
        tasks.add(task);
    }

    public static ArrayList<Vehicle> getVehicles() {
        return vehicles;
    }

    public static void addVehicle(Vehicle vehicle) {
        vehicles.add(vehicle);
    }
}
