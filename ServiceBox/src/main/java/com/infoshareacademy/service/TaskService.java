package com.infoshareacademy.service;

import com.infoshareacademy.core.ConsoleInput;
import com.infoshareacademy.model.Task;
import com.infoshareacademy.model.Vehicle;

public class TaskService {

    public Task createTask(){
        System.out.println("Tworzenie nowego zlecenia");
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        // id, nazwa klienta(imie, nazwisko), pojazd - numer rejestracyjny, opis naprawy podany przez klienta)

        int number = ConsoleInput.getInt("Wprowadz numer zlecenia:", "Bledny numer zlecenia");
        String name = ConsoleInput.getString("Wprowadz imie i nazwisko: ", "Bledna nazwa");
        String plateNumber = ConsoleInput.getString("Wprowadz numer rejestracyjny: ", "Bledna wartosc");
        String repairDescription = ConsoleInput.getString("Wprowadz opis naprawy podany przez klienta: ", "Bledny opis");

        Vehicle car = new Vehicle(plateNumber);
        Task task = new Task(number, name, car, repairDescription);
        return task;
    }
}
