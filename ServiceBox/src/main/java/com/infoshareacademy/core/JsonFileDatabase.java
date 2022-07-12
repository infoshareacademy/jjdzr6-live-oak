package com.infoshareacademy.core;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.infoshareacademy.model.Task;
import com.infoshareacademy.model.Vehicle;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class JsonFileDatabase implements DatabaseInterface {
    private static final Path TASK_DB_PATH = Path.of("src", "main", "resources", "task.json");
    private static final Path VEHICLE_DB_PATH = Path.of("src", "main", "resources", "vehicle.json");
    private Gson gson;
    private ArrayList<Task> tasks;
    private ArrayList<Vehicle> vehicles;

    public JsonFileDatabase() {
        initGson();
        loadDataFromFiles();
    }

    @Override
    public ArrayList<Task> getTasks() {
        return tasks;
    }

    @Override
    public ArrayList<Vehicle> getVehicles() {
        return vehicles;
    }

    @Override
    public void addTask(Task task) {
        tasks.add(task);
        saveTasks();
    }

    @Override
    public void addVehicle(Vehicle vehicle) {
        vehicles.add(vehicle);
        saveVehicles();
    }

    public void saveVehicles() {
        String json = gson.toJson(vehicles);

        try {
            Files.writeString(VEHICLE_DB_PATH, json);
        } catch (Exception ex) {
            System.out.println("Nie mozna zapisac do pliku " + VEHICLE_DB_PATH.toString());
        }
    }

    public void saveTasks() {
        String json = gson.toJson(tasks);

        try {
            Files.writeString(TASK_DB_PATH, json);
        } catch (Exception ex) {
            System.out.println("Nie mozna zapisac do pliku " + TASK_DB_PATH.toString());
        }
    }

    private void initGson() {
        GsonBuilder builder = new GsonBuilder();
        builder.setPrettyPrinting();
        builder.registerTypeAdapter(LocalDate.class, new LocalDateConverter());
        gson = builder.create();
    }

    private void loadDataFromFiles() {
        try {
            // load vehicles
            vehicles = gson.fromJson(Files.readString(VEHICLE_DB_PATH), new TypeToken<List<Vehicle>>() {
            }.getType());

            // load tasks
            tasks = gson.fromJson(Files.readString(TASK_DB_PATH), new TypeToken<List<Task>>() {
            }.getType());

            // TODO: load clients
        } catch (IOException e) {
            System.out.println("Nie mozna zaladowac danych z plikow JSON");
        }
    }
}
