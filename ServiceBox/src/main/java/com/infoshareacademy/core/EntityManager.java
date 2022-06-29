package com.infoshareacademy.core;

import com.infoshareacademy.repository.TaskRepository;
import com.infoshareacademy.repository.VehicleRepository;

public class EntityManager {
    private static EntityManager instance;
    private final DatabaseInterface database = new JsonFileDatabase();
    private final TaskRepository taskRepository = new TaskRepository(database);
    private final VehicleRepository vehicleRepository = new VehicleRepository(database);

    private EntityManager() {
    }

    public static EntityManager getInstance() {
        if (instance == null) {
            instance = new EntityManager();
        }

        return instance;
    }

    public TaskRepository getTaskRepository() {
        return taskRepository;
    }

    public VehicleRepository getVehicleRepository() {
        return vehicleRepository;
    }

    public DatabaseInterface getDatabase() {
        return database;
    }
}
