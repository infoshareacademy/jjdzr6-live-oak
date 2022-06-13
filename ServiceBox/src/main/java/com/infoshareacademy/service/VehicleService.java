package com.infoshareacademy.service;

import com.infoshareacademy.core.ConsoleInput;
import com.infoshareacademy.core.ConsoleOutput;
import com.infoshareacademy.core.DatabaseInterface;
import com.infoshareacademy.core.MemoryDatabase;
import com.infoshareacademy.model.Vehicle;
import com.infoshareacademy.repository.VehicleRepository;

public class VehicleService {
    private final DatabaseInterface database = new MemoryDatabase();

    public Vehicle createVehicle(String plateNumber) {
        VehicleRepository vehicleRepository = new VehicleRepository(database);
        int id = vehicleRepository.getNextId();

        System.out.println(ConsoleOutput.ROW_SEPARATOR);
        System.out.println("Dodawanie nowego pojazdu");
        System.out.println(ConsoleOutput.ROW_SEPARATOR);

        if (plateNumber.isEmpty()) {
            plateNumber = ConsoleInput.getString("Podaj nowy numer rejestracyjny: ");
        }

        String manufacturer = ConsoleInput.getString("Wprowadz marke pojazdu: ");
        String model = ConsoleInput.getString("Wprowadz model pojazdu: ");
        float engineCapacity = ConsoleInput.getCapacity("Wprowadz pojemnosc silnika: ");
        int productionYear = ConsoleInput.getYear("Wprowadz rok produkcji: ");

        Vehicle vehicle = new Vehicle(id, plateNumber, manufacturer, model, engineCapacity, productionYear);

        database.addVehicle(vehicle);
        ConsoleOutput.alert("Dodano nowy pojazd");
        ConsoleInput.waitForEnter();

        return vehicle;
    }
}
