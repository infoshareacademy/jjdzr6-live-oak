package com.infoshareacademy.service;

import com.infoshareacademy.core.ConsoleInput;
import com.infoshareacademy.core.ConsoleOutput;
import com.infoshareacademy.core.DatabaseInterface;
import com.infoshareacademy.core.MemoryDatabase;
import com.infoshareacademy.model.Task;
import com.infoshareacademy.model.Vehicle;
import com.infoshareacademy.repository.VehicleRepository;

import java.util.ArrayList;

public class VehicleService {
    private final DatabaseInterface db = new MemoryDatabase();
    private final VehicleRepository vehicleRepository = new VehicleRepository(db);
    public Vehicle createVehicle(String plateNumber) {

        int id = vehicleRepository.getNextId();

        System.out.println(ConsoleOutput.ROW_SEPARATOR);
        System.out.println("Dodawanie nowego pojazdu");
        System.out.println(ConsoleOutput.ROW_SEPARATOR);

        if (plateNumber.isEmpty()) {
            plateNumber = ConsoleInput.getString("Podaj nowy numer rejestracyjny: ");
        }

        String manufacturer = ConsoleInput.getString("Wprowadz marke pojazdu: ");
        String model = ConsoleInput.getString("Wprowadz model pojazdu: ");
        String engineCapacity = ConsoleInput.getString("Wprowadz pojemnosc silnika: ");
        int productionYear = ConsoleInput.getInt("Wprowadz rok produkcji: ");

        Vehicle vehicle = new Vehicle(id, plateNumber, manufacturer, model, engineCapacity, productionYear);

        db.addVehicle(vehicle);
        ConsoleOutput.alert("Dodano nowy pojazd");
        ConsoleInput.waitForEnter();

        return vehicle;
    }

    public void showAllVeh() {
        System.out.println(ConsoleOutput.ROW_SEPARATOR);
        System.out.println("Wszystkie pojazy w bazie");
        System.out.println(ConsoleOutput.ROW_SEPARATOR);

        ArrayList<Vehicle> myAllVehicle = vehicleRepository.findAll();

        if (myAllVehicle.isEmpty()) {
            ConsoleOutput.alert("Nie dodano jeszcze zadnego pojazdu do bazy");
        } else {
            for (Vehicle vehicle : myAllVehicle) {
                System.out.println(vehicle);
            }
        }

        ConsoleInput.waitForEnter();
    }


}

