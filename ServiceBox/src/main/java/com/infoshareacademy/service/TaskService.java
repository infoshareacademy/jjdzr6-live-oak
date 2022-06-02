package com.infoshareacademy.service;

import com.infoshareacademy.core.ConsoleInput;
import com.infoshareacademy.core.DatabaseInterface;
import com.infoshareacademy.core.MemoryDatabase;
import com.infoshareacademy.core.ServiceContainer;
import com.infoshareacademy.model.Task;
import com.infoshareacademy.model.Vehicle;
import com.infoshareacademy.repository.TaskRepository;
import com.infoshareacademy.repository.VehicleRepository;

import java.util.ArrayList;

public class TaskService {
    private final DatabaseInterface db = new MemoryDatabase();

    public void createTask() {
        VehicleRepository vehicleRepository = new VehicleRepository(db);
        TaskRepository taskRepository = new TaskRepository(db);

        // id = number of elements (tasks) + 1
        int id = taskRepository.getNextId();

        // get data from user
        System.out.println("Tworzenie nowego zlecenia nr " + id + "/2022");
        System.out.println(ConsoleInput.ROW_SEPARATOR);
        String name = ConsoleInput.getString("Wprowadz imie i nazwisko: ", "Bledna nazwa");
        String repairDescription = ConsoleInput.getString("Wprowadz opis naprawy podany przez klienta: ", "Bledny opis");
        String plateNumber = ConsoleInput.getString("Wprowadz numer rejestracyjny pojazdu: ", "Bledny numer");

        Vehicle vehicle;

        try {
            // try to find vehicle with specified plate number
            vehicle = vehicleRepository.findByPlateNumber(plateNumber);
        } catch (Exception e) {
            // not found - add new vehicle
            vehicle = ServiceContainer.getInstance().getVehicleService().createVehicle(plateNumber);
        }

        // create a new Task
        Task task = new Task(id, name, vehicle, repairDescription);
        // ...and add to the database (currently store in static field)
        db.addTask(task);

        System.out.println("Utworzono nowe zlecenie naprawy");
    }

    public void showAll() {
        System.out.println("Wszystkie zlecenia naprawy");
        System.out.println(ConsoleInput.ROW_SEPARATOR);

        TaskRepository repo = new TaskRepository(db);
        ArrayList<Task> myAllTasks = repo.findAll();

        if (myAllTasks.isEmpty()) {
            System.out.println("Nie dodano jeszcze zadnego zlecenia naprawy");
        } else {
            for (Task task : myAllTasks) {
                System.out.println(task);
            }
        }
    }

    public void showSingleTask() {
        System.out.println("Wyszukaj zlecenie naprawy");
        System.out.println(ConsoleInput.ROW_SEPARATOR);

        int id = ConsoleInput.getInt("Podaj numer zlecenia naprawy: ", "Bledny numer zlecenia");
        TaskRepository repo = new TaskRepository(db);

        try {
            Task task = repo.findById(id);
            System.out.println(task);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
