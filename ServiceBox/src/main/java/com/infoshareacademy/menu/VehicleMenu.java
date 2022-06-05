package com.infoshareacademy.menu;

import com.infoshareacademy.core.ConsoleInput;
import com.infoshareacademy.core.ConsoleOutput;
import com.infoshareacademy.service.VehicleService;

import java.util.List;

public class VehicleMenu extends AbstractMenu {
    public VehicleMenu() {
        options.add(ConsoleOutput.ROW_SEPARATOR);
        options.add("Baza pojazdow");
        options.add(ConsoleOutput.ROW_SEPARATOR);
        options.add("1. Dodaj nowy pojazd");
        options.add("2. Wyswietl wszystkie pojazdy");
        options.add("3. Wyszukaj/Zaktualizuj dane pojazdu");
        options.add("0. Wroc do menu glownego");
        options.add(ConsoleOutput.ROW_SEPARATOR);
    }

    @Override
    public void showMainMenu(List<String> options) {

    }

    @Override
    public void start() {

        int option = 0;
        do {
            showMainMenu(options);
            option = ConsoleInput.getIntFromRange("Wybierz jedna z opcji (0-3): ", 0, 3);
            VehicleService vehicleService = new VehicleService();

            switch (option) {
                case 1:
                    vehicleService.createVehicle("");
                    break;

                case 2:
                    // TODO: wyswietlanie listy wszystkich pojazdow
                    System.out.println("Lista pojazdow...");
                    break;

                case 3:
                    // TODO: wyszukiwanie, aktualizacja danych pojazdu
                    System.out.println("Wyszukiwanie/aktualizacja pojazdu...");
                    break;

                default:
            }

        } while (option != EXIT_OPTION);
    }
}

