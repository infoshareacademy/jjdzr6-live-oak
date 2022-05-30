package com.infoshareacademy.service;

import com.infoshareacademy.core.ConsoleInput;
import com.infoshareacademy.core.DatabaseInterface;
import com.infoshareacademy.core.MemoryDatabase;
import com.infoshareacademy.core.ServiceContainer;
import com.infoshareacademy.model.Task;
import com.infoshareacademy.model.Vehicle;
import com.infoshareacademy.repository.TaskRepository;
import com.infoshareacademy.repository.VehicleRepository;
import com.infoshareacademy.security.Session;

import java.util.ArrayList;
import java.util.Scanner;

public class TaskService {
    private final String waves = "~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~";

    public Task createTask() {
        int id = Session.generateId();

        System.out.println("Tworzenie nowego zlecenia nr " + id + "/2022");
        System.out.println(waves);

        String name = ConsoleInput.getString("Wprowadz imie i nazwisko: ", "Bledna nazwa");
        String repairDescription = ConsoleInput.getString("Wprowadz opis naprawy podany przez klienta: ", "Bledny opis");
        String plateNumber = ConsoleInput.getString("Wprowadz numer rejestracyjny pojazdu: ", "Bledny opis");

        MemoryDatabase db = new MemoryDatabase();
        VehicleRepository repo = new VehicleRepository(db);

        Vehicle vehicle ;

        try {
             vehicle = repo.findByPlateNumber(plateNumber);
        } catch (Exception e) {
             vehicle = ServiceContainer.getInstance().getVehicleService().createVehicle(plateNumber);
        }


        Task task = new Task(id, name, vehicle, repairDescription);
        db.addTask(task);

        return task;
    }

    public void showAll() {
        System.out.println("Wszystkie zlecenia naprawy");
        System.out.println(waves);

        MemoryDatabase db = new MemoryDatabase();
        TaskRepository repo = new TaskRepository(db);

        ArrayList<Task> myAllTask = repo.findAll();
        if (myAllTask.isEmpty()) {
            System.out.println("Brak zleceń.");
        }else {
            for (Task task : myAllTask) {
                System.out.println(task);
            }
        }
    }

    public void showSingleTask() {
        System.out.println("Wyszukaj zlecenie naprawy");
        System.out.println(waves);

        int id = ConsoleInput.getInt("Podaj numer zlecenia naprawy: ", "Bledny numer zlecani");
        MemoryDatabase db = new MemoryDatabase();
        TaskRepository repo = new TaskRepository(db);

        try {
            Task task = repo.findById(id);
            System.out.println(task);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }
}
