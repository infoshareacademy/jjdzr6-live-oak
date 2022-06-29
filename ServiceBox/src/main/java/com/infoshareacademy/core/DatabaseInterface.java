package com.infoshareacademy.core;

import com.infoshareacademy.model.Task;
import com.infoshareacademy.model.Vehicle;

import java.util.ArrayList;

public interface DatabaseInterface {
    ArrayList<Task> getTasks();

    ArrayList<Vehicle> getVehicles();

    void addTask(Task task);

    void addVehicle(Vehicle vehicle);

    void saveTasks();

    void saveVehicles();
}
