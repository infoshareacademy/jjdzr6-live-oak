package com.infoshareacademy.core;

import com.infoshareacademy.model.Task;
import com.infoshareacademy.model.Vehicle;

import java.util.ArrayList;

public class JsonFileDatabase implements DatabaseInterface {
    @Override
    public ArrayList<Task> getTasks() {
        // read all Tasks from JSON file
        ArrayList<Task> tasks = new ArrayList<>();
        return tasks;
    }

    @Override
    public ArrayList<Vehicle> getVehicles() {
        // read all Vehicles from JSON file
        ArrayList<Vehicle> vehicles = new ArrayList<>();
        return vehicles;
    }

    @Override
    public void addTask(Task task) {
        // add (save) new Task in JSON file
    }

    @Override
    public void addVehicle(Vehicle vehicle) {
        // add (save) new Vehicle in JSON file
    }
}
