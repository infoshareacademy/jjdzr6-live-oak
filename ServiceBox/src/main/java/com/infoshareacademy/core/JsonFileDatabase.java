package com.infoshareacademy.core;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.infoshareacademy.model.Task;
import com.infoshareacademy.model.Vehicle;

import java.io.IOException;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

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
        ArrayList<Task> tasks = new ArrayList<>();
        try {
            tasks = gson.fromJson(Files.readString(TASK_DB_PATH), new TypeToken<List<Task>>() {
            }.getType());
        } catch (IOException e) {
            System.out.println("error");
        }

        return tasks;
    }

    @Override
    public ArrayList<Vehicle> getVehicles() {

        ArrayList<Vehicle> vehicles = new ArrayList<>();
        try {
            vehicles = gson.fromJson(Files.readString(VEHICLE_DB_PATH), new TypeToken<List<Vehicle>>() {
            }.getType());
        } catch (IOException e) {
            System.out.println("error");
        }
        return vehicles;
    }

    @Override
    public void addTask(Task task) {
        // add (save) new Task in JSON file
        ArrayList<Task> tableTask = getTasks();
        tableTask.add(task);
        String json = gson.toJson(tableTask);
        try {
            Files.writeString(TASK_DB_PATH, json);
        } catch (Exception ex) {
            System.out.println("Nie mozna zapisac do pliku.");
        }

    }

    @Override
    public void addVehicle(Vehicle vehicle) {
        // add (save) new Vehicle in JSON file
        ArrayList<Vehicle> tableVehicle = getVehicles();
        tableVehicle.add(vehicle);
        String json = gson.toJson(tableVehicle);
        try {
            Files.writeString(VEHICLE_DB_PATH, json);
        } catch (Exception ex) {
            System.out.println("Nie mozna zapisac do pliku.");
        }

    }
}
