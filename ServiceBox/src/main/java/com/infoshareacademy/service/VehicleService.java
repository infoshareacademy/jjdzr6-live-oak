package com.infoshareacademy.service;

import com.infoshareacademy.core.ConsoleInput;
import com.infoshareacademy.core.MemoryDB;
import com.infoshareacademy.model.Vehicle;

public class VehicleService {
    private final String waves = "~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~";

    public Vehicle createVehicle() {
        System.out.println("Dodawanie nowego samochodu");
        System.out.println(waves);

        String plateNumber = ConsoleInput.getString("Wprowadz numer rejestracyjny: ", "Bledna wartosc");
        String manufacturer = ConsoleInput.getString("Wprowadz marke pojazdu: ", "Bledna wartosc");
        String model = ConsoleInput.getString("Wprowadz model pojazdu: ", "Bledna wartosc");
        String engineCapacity = ConsoleInput.getString("Wprowadz pojemnosc silnika: ", "Bledna wartosc");
        int productionYear = ConsoleInput.getInt("Wprowadz rok produkcji: ", "Bledna wartosc");

        Vehicle vehicle = new Vehicle(plateNumber, manufacturer, model, engineCapacity, productionYear);
        MemoryDB.addVehicle(vehicle);
        return vehicle;
    }
}
