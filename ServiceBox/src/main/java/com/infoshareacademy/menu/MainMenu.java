package com.infoshareacademy.menu;

import com.infoshareacademy.core.ConsoleInput;
import com.infoshareacademy.core.ConsoleOutput;
import com.infoshareacademy.service.TaskService;

public class MainMenu {
    private static final int EXIT_OPTION = 0;

    private void showMainMenu() {
        System.out.println(ConsoleOutput.ROW_SEPARATOR);
        System.out.println("      Witamy w systemie ServiceBox");
        System.out.println(ConsoleOutput.ROW_SEPARATOR);
        System.out.println("1. Zlecenia naprawy");
        System.out.println("2. Baza pojazdów");
        System.out.println("0. Zakoncz");
        System.out.println(ConsoleOutput.ROW_SEPARATOR);
    }

    public void start() {
        int option = 0;

        do {
            showMainMenu();
            option = ConsoleInput.getIntFromRange("Wybierz jedna z opcji (0-2): ", 0, 2);

            switch (option) {
                case 1:
                    TaskMenu taskMenu = new TaskMenu();
                    taskMenu.start();
                    break;

                case 2:
                    VehicleMenu vehicleMenu = new VehicleMenu();
                    vehicleMenu.start();
                    break;

                default:
                    ConsoleOutput.alert("Zakonczono program");
            }

        } while (option != EXIT_OPTION);
    }
}

