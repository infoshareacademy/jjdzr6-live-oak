package com.infoshareacademy.service;

import com.infoshareacademy.core.ConsoleInput;
import com.infoshareacademy.core.DatabaseInterface;
import com.infoshareacademy.core.MemoryDatabase;
import com.infoshareacademy.core.ServiceContainer;
import com.infoshareacademy.model.Task;
import com.infoshareacademy.model.Vehicle;
import com.infoshareacademy.security.Session;

public class TaskService {
    private final String waves = "~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~";

    public Task createTask(){
        int id = Session.generateId();

        System.out.println("Tworzenie nowego zlecenia nr " + id + "/2022");
        System.out.println(waves);

        String name = ConsoleInput.getString("Wprowadz imie i nazwisko: ", "Bledna nazwa");
        String repairDescription = ConsoleInput.getString("Wprowadz opis naprawy podany przez klienta: ", "Bledny opis");

        Vehicle vehicle = ServiceContainer.getInstance().getVehicleService().createVehicle();
        Task task = new Task(id, name, vehicle, repairDescription);

        DatabaseInterface db = new MemoryDatabase();
        db.addTask(task);

        return task;
    }

    public void showAll() {
        System.out.println("Wszystkie zlecenia naprawy");
        System.out.println(waves);

        // TODO: wyświetlanie wszystkich utworzonych zleceń naprawy
    }

    public void showSingleTask() {
        System.out.println("Wyszukaj zlecenie naprawy");
        System.out.println(waves);

        // TODO: wyświetlanie zlecenia naprawy o wskazanym ID

        // 1. pobranie id
        // 2. wyszukanie zlecenia
        // 3. wyświetlenie zlecenia lub informacji, że zlecenie o podanym id nie istnieje
    }
}
