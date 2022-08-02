package com.infoshareacademy.core;

import com.infoshareacademy.model.Task;
import com.infoshareacademy.model.Vehicle;
import com.infoshareacademy.model.Client;

import java.util.ArrayList;

public interface DatabaseInterface {
    ArrayList<Task> getTasks();

    ArrayList<Vehicle> getVehicles();

    ArrayList<Client> getClients();

    void addTask(Task task);

    void addVehicle(Vehicle vehicle);

    void addClient(Client client);

    void saveTasks();

    void saveVehicles();

    void saveClients();
}
