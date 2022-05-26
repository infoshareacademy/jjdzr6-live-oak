package com.infoshareacademy.core;

import com.infoshareacademy.service.TaskService;
import com.infoshareacademy.service.VehicleService;

public class Container {
    TaskService taskService = new TaskService();
    VehicleService vehicleService = new VehicleService();

    public TaskService getTaskService() {
        return taskService;
    }

    public VehicleService getVehicleService() {
        return vehicleService;
    }
}
