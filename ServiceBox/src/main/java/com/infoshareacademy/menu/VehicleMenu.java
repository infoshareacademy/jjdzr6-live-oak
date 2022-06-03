package com.infoshareacademy.menu;

import com.infoshareacademy.core.ConsoleInput;
import com.infoshareacademy.core.ConsoleOutput;
import com.infoshareacademy.service.VehicleService;

public class VehicleMenu {
    private static final int EXIT_OPTION = 0;

    private void showMainMenu() {
        System.out.println(ConsoleOutput.ROW_SEPARATOR);
        System.out.println("      Baza pojazdow");
        System.out.println(ConsoleOutput.ROW_SEPARATOR);
        System.out.println("1. Dodaj nowy pojazd");
        System.out.println("2. Wyswietl wszystkie pojazdy");
        System.out.println("3. Wyszukaj/Zaktualizuj dane pojazdu");
        System.out.println("0. Wroc do menu glownego");
        System.out.println(ConsoleOutput.ROW_SEPARATOR);
    }

    public void start() {

        int option = 0;
        do {
            showMainMenu();
            option = ConsoleInput.getIntFromRange("Wybierz jedna z opcji (0-3): ", 0, 3);
            VehicleService vehicleService = new VehicleService();

            switch (option) {
                case 1:
                    // TODO: dodawanie pojazdu
                    System.out.println("Dodawanie pojazdu...");
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

