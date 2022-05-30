package com.infoshareacademy.core;

import com.infoshareacademy.service.TaskService;
import com.infoshareacademy.service.VehicleService;

public class ServiceContainer {
    private static ServiceContainer instance;

    private ServiceContainer() {}

    public static ServiceContainer getInstance() {
        if (instance == null) {
            instance = new ServiceContainer();
        }

        return instance;
    }

    TaskService taskService = new TaskService();
    VehicleService vehicleService = new VehicleService();

    public TaskService getTaskService() {
        return taskService;
    }

    public VehicleService getVehicleService() {
        return vehicleService;
    }
}
