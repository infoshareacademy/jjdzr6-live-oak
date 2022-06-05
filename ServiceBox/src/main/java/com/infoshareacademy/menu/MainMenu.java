package com.infoshareacademy.menu;

import com.infoshareacademy.core.ConsoleInput;
import com.infoshareacademy.core.ConsoleOutput;

public class MainMenu extends AbstractMenu {

    public MainMenu() {
        options.add(ConsoleOutput.ROW_SEPARATOR);
        options.add("      Witamy w systemie ServiceBox");
        options.add(ConsoleOutput.ROW_SEPARATOR);
        options.add("1. Zlecenia naprawy");
        options.add("2. Baza pojazdow");
        options.add("0. Zakoncz");
        options.add(ConsoleOutput.ROW_SEPARATOR);
    }


    @Override
    public void start() {
        int option = 0;

        do {
            showMainMenu(options);
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

