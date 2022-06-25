package com.infoshareacademy.service;

import com.infoshareacademy.core.*;
import com.infoshareacademy.model.Vehicle;
import com.infoshareacademy.repository.VehicleRepository;

import java.util.ArrayList;

public class VehicleService {

    private final DatabaseInterface database = new JsonFileDatabase();
   private final VehicleRepository vehicleRepository = new VehicleRepository(database);
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
        float engineCapacity = ConsoleInput.getCapacity("Wprowadz pojemnosc silnika: ");
        int productionYear = ConsoleInput.getYear("Wprowadz rok produkcji: ");

        Vehicle vehicle = new Vehicle(id, plateNumber, manufacturer, model, engineCapacity, productionYear);

        database.addVehicle(vehicle);
        ConsoleOutput.alert("Dodano nowy pojazd");
        ConsoleInput.waitForEnter();

        return vehicle;
    }

    public void showAllVehicle() {
        System.out.println(ConsoleOutput.ROW_SEPARATOR);
        System.out.println("Wszystkie pojazdy w bazie");
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
    public void editVehicle(Vehicle vehicle) {
        System.out.println(ConsoleOutput.ROW_SEPARATOR);
        System.out.println("Edycja pojazdu o numerze rejestracyjnym " + vehicle.getPlateNumber());
        System.out.println(ConsoleOutput.ROW_SEPARATOR);
        int option = 0;
        do {
            System.out.println("1. Popraw numer rejestracyjny: ");
            System.out.println("2. Popraw marke pojazdu: ");
            System.out.println("3. Popraw model pojazdu: ");
            System.out.println("4. Popraw pojemnosc silnika: ");
            System.out.println("5. Popraw rok produkcji: ");
            System.out.println("0. Zakoncz");
            System.out.println(ConsoleOutput.ROW_SEPARATOR);
            option = ConsoleInput.getIntFromRange("Wybierz pole do zmiany: ", 0, 5);
            switch (option) {
                case 1:
                    String plateNumber = ConsoleInput.getString("Podaj nowy numer rejestracyjny: ");
                    vehicle.setPlateNumber(plateNumber);
                    break;
                case 2:
                    String manufacturer = ConsoleInput.getString("Podaj nowa nazwe producenta: ");
                    vehicle.setManufacter(manufacturer);
                    break;
                case 3:
                    String carName = ConsoleInput.getString("Podaj nowy model pojazdu: ");
                    vehicle.setCarName(carName);
                    break;
                case 4:
                    Float engineCapacity = ConsoleInput.getFloat("Podaj nowa pojemnosc silnika: ");
                    vehicle.setEngineCapacity(engineCapacity);
                    break;
                case 5:
                    int productionYear = ConsoleInput.getInt("Podaj nowy rok produkcji: ");
                    vehicle.setProductionYear(productionYear);
                    break;
                default:
                    ConsoleOutput.alert("Zakonczono aktualizacje pojazdu o numerze rejestracyjnym: " + vehicle.getPlateNumber());
            }
            ConsoleOutput.alert("Dane zostaly zaktualizowane");
            ConsoleInput.waitForEnter();
        } while (option != 0);
    }
    public void findAndUpdateVehicle() {
        System.out.println(ConsoleOutput.ROW_SEPARATOR);
        System.out.println("Wyszukaj pojazd po numerze rejestracyjnym");
        System.out.println(ConsoleOutput.ROW_SEPARATOR);
        String plateNumber = ConsoleInput.getString("Podaj numer rejestracyjny pojazdu: ");
        try {
            Vehicle vehicle = vehicleRepository.findByPlateNumber(plateNumber);
            System.out.println(vehicle);
            String letter = ConsoleInput.getString("Czy chcesz zaktualizowac dane pojazdu?  Wybierz opcje: [T/N] ");
            if (letter.equalsIgnoreCase("T")) {
                editVehicle(vehicle);
            }
        } catch (Exception e) {
            ConsoleOutput.alert(e.getMessage());
            ConsoleInput.waitForEnter();
        }
    }

}


