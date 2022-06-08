package com.infoshareacademy.service;

import com.infoshareacademy.core.*;
import com.infoshareacademy.model.Task;
import com.infoshareacademy.model.Vehicle;
import com.infoshareacademy.repository.TaskRepository;
import com.infoshareacademy.repository.VehicleRepository;

import java.util.ArrayList;

public class TaskService {
    private final DatabaseInterface db = new JsonFileDatabase();
    private final TaskRepository taskRepository = new TaskRepository(db);
    private final VehicleRepository vehicleRepository = new VehicleRepository(db);

    public void createTask() {
        int taskId = taskRepository.getNextId();
        Vehicle vehicle;

        System.out.println(ConsoleOutput.ROW_SEPARATOR);
        System.out.println("Tworzenie nowego zlecenia nr: " + taskId);
        System.out.println(ConsoleOutput.ROW_SEPARATOR);
        String name = ConsoleInput.getString("Wprowadz imie i nazwisko: ");
        String repairDescription = ConsoleInput.getString("Wprowadz opis naprawy podany przez klienta: ");
        String plateNumber = ConsoleInput.getString("Wprowadz numer rejestracyjny pojazdu: ");

        try {
            // try to find vehicle with specified plate number
            vehicle = vehicleRepository.findByPlateNumber(plateNumber);
        } catch (Exception e) {
            // not found - add new vehicle
            VehicleService vehicleService = new VehicleService();
            ConsoleOutput.alert("Brak pojazdu w bazie");
            vehicle = vehicleService.createVehicle(plateNumber);
        }

        // create a new Task
        Task task = new Task(taskId, name, vehicle, repairDescription);
        // ...and add to the database
        db.addTask(task);

        ConsoleOutput.alert("Utworzono nowe zlecenie naprawy");
        ConsoleInput.waitForEnter();
    }

    public void editTask(Task task) {
        System.out.println(ConsoleOutput.ROW_SEPARATOR);
        System.out.println("Edycja zlecenia nr " + task.getId());
        System.out.println(ConsoleOutput.ROW_SEPARATOR);

        int option = 0;

        do {
            System.out.println("1. Imie i Nazwisko klienta ");
            System.out.println("2. Opis naprawy ");
            System.out.println("3. Numer rejestracyjny pojazdu ");
            System.out.println("0. Zakoncz");
            System.out.println(ConsoleOutput.ROW_SEPARATOR);
            option = ConsoleInput.getIntFromRange("Wybierz pole do zmiany: ", 0, 3);

            switch (option) {
                case 1:
                    String clientName = ConsoleInput.getString("Podaj nowe imie i nazwisko klienta: ");
                    task.setClientName(clientName);
                    break;

                case 2:
                    String repairDescription = ConsoleInput.getString("Podaj nowy opis naprawy: ");
                    task.setRepairDescription(repairDescription);
                    break;

                case 3:
                    String plateNumber = ConsoleInput.getString("Podaj nowy numer rejestracyjny: ");
                    task.getVehicle().setPlateNumber(plateNumber);
                    break;

                default:
                    ConsoleOutput.alert("Zakonczono aktualizacje zlecenia numer: " + task.getId());
            }

            ConsoleOutput.alert("Dane zostaly zaktualizowane");
            ConsoleInput.waitForEnter();
        } while (option != 0);
    }

    public void showAll() {
        System.out.println(ConsoleOutput.ROW_SEPARATOR);
        System.out.println("Wszystkie zlecenia naprawy");
        System.out.println(ConsoleOutput.ROW_SEPARATOR);

        ArrayList<Task> myAllTasks = taskRepository.findAll();

        if (myAllTasks.isEmpty()) {
            ConsoleOutput.alert("Nie dodano jeszcze zadnego zlecenia naprawy");
        } else {
            for (Task task : myAllTasks) {
                System.out.println(task);
            }
        }

        ConsoleInput.waitForEnter();
    }

    public void findAndUpdateTask() {
        System.out.println(ConsoleOutput.ROW_SEPARATOR);
        System.out.println("Wyszukaj zlecenie naprawy");
        System.out.println(ConsoleOutput.ROW_SEPARATOR);

        int id = ConsoleInput.getInt("Podaj numer zlecenia naprawy: ");

        try {
            Task task = taskRepository.findById(id);
            System.out.println(task);
            String letter = ConsoleInput.getString("Czy chcesz zaktualizowac zlecenie?  Wybierz opcje: [T/N] ");

            if (letter.equalsIgnoreCase("T")) {
                editTask(task);
            }
        } catch (Exception e) {
            ConsoleOutput.alert(e.getMessage());
            ConsoleInput.waitForEnter();
        }
    }
}
