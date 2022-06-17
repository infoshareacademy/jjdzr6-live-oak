package com.infoshareacademy.core;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.infoshareacademy.model.Task;
import com.infoshareacademy.model.Vehicle;

import java.nio.file.Path;
import java.time.LocalDate;
import java.util.ArrayList;

public class JsonFileDatabase implements DatabaseInterface {
    private final Gson gson;
    private static final Path TASK_DB_PATH = Path.of("src", "main", "resources", "task.json");
    private static final Path VEHICLE_DB_PATH = Path.of("src", "main", "resources", "vehicle.json");

    public JsonFileDatabase() {
        GsonBuilder builder = new GsonBuilder();
        builder.setPrettyPrinting();
        builder.registerTypeAdapter(LocalDate.class, new LocalDateConverter());
        gson = builder.create();
    }

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
