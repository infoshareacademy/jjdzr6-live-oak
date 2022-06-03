package com.infoshareacademy.menu;

import com.infoshareacademy.core.ConsoleInput;
import com.infoshareacademy.core.ConsoleOutput;
import com.infoshareacademy.service.TaskService;

public class TaskMenu {
    private static final int EXIT_OPTION = 0;

    private void showMainMenu() {
        System.out.println(ConsoleOutput.ROW_SEPARATOR);
        System.out.println("      Zlecenia naprawy");
        System.out.println(ConsoleOutput.ROW_SEPARATOR);
        System.out.println("1. Utworz nowe zlecenie naprawy");
        System.out.println("2. Wyswietl wszystkie zlecenia naprawy");
        System.out.println("3. Wyszukaj/Zaktualizuj zlecenie naprawy");
        System.out.println("0. Wroc do menu glownego");
        System.out.println(ConsoleOutput.ROW_SEPARATOR);
    }

    public void start() {
        int option = 0;

        do {
            showMainMenu();
            option = ConsoleInput.getIntFromRange("Wybierz jedna z opcji (0-3): ", 0, 3);
            TaskService taskService = new TaskService();

            switch (option) {
                case 1:
                    taskService.createTask();
                    break;

                case 2:
                    taskService.showAll();
                    break;

                case 3:
                    taskService.findAndUpdateTask();
                    break;

                default:
            }

        } while (option != EXIT_OPTION);
    }
}

