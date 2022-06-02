package com.infoshareacademy.service;

import com.infoshareacademy.core.ConsoleInput;
import com.infoshareacademy.core.DatabaseInterface;
import com.infoshareacademy.core.MemoryDatabase;
import com.infoshareacademy.model.Vehicle;
import com.infoshareacademy.repository.VehicleRepository;

public class VehicleService {
    private final DatabaseInterface db = new MemoryDatabase();

    public Vehicle createVehicle(String plateNumber) {
        VehicleRepository vehicleRepository = new VehicleRepository(db);
        int id = vehicleRepository.getNextId();

        System.out.println("Dodawanie nowego samochodu");
        System.out.println(ConsoleInput.ROW_SEPARATOR);

        String manufacturer = ConsoleInput.getString("Wprowadz marke pojazdu: ", "Bledna wartosc");
        String model = ConsoleInput.getString("Wprowadz model pojazdu: ", "Bledna wartosc");
        String engineCapacity = ConsoleInput.getString("Wprowadz pojemnosc silnika: ", "Bledna wartosc");
        int productionYear = ConsoleInput.getInt("Wprowadz rok produkcji: ", "Bledna wartosc");

        Vehicle vehicle = new Vehicle(id, plateNumber, manufacturer, model, engineCapacity, productionYear);

        db.addVehicle(vehicle);

        return vehicle;
    }
}
