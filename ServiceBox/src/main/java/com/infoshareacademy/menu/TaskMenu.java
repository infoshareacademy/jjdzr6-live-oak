package com.infoshareacademy.menu;

import com.infoshareacademy.core.ConsoleInput;
import com.infoshareacademy.core.ConsoleOutput;
import com.infoshareacademy.service.TaskService;

import java.util.ArrayList;
import java.util.List;

public class TaskMenu extends AbstractMenu {

    public TaskMenu() {
        options.add(ConsoleOutput.ROW_SEPARATOR);
        options.add("Zlecenia naprawy");
        options.add("1. Utworz nowe zlecenie naprawy");
        options.add("2. Wyswietl wszystkie zlecenia naprawy");
        options.add("3. Wyszukaj/Zaktualizuj zlecenie naprawy");
        options.add("4. Wyszukaj zlecenia po numerze rejestracyjnym pojazdu");
        options.add("5. Wyszukaj zlecenia po imieniu i nazwisku klienta");
        options.add("0. Wroc do menu glownego");
        options.add(ConsoleOutput.ROW_SEPARATOR);
    }

    @Override
    public void start() {
        int option = 0;

        do {
            showMainMenu(options);
            option = ConsoleInput.getIntFromRange("Wybierz jedna z opcji (0-5): ", 0, 5);
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

                case 4:
                    taskService.findTasksByPlateNumber();
                    break;

                case 5:
                    taskService.findTasksByOwnerName();
                    break;
                default:
            }

        } while (option != EXIT_OPTION);
    }
}

