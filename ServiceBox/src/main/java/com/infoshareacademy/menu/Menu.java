package com.infoshareacademy.menu;

import com.infoshareacademy.core.ConsoleInput;
import com.infoshareacademy.core.ServiceContainer;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Menu {
    private void showMainMenu() {
        System.out.println("      Witamy w systemie ServiceBox      ");
        System.out.println(ConsoleInput.ROW_SEPARATOR);
        System.out.println("1. Utworz nowe zlecenie naprawy");
        System.out.println("2. Wyswietl wszystkie zlecenia naprawy");
        System.out.println("3. Wyszukaj/Zaktualizuj zlecenie naprawy");
        System.out.println("4. Zakoncz");
        System.out.println(ConsoleInput.ROW_SEPARATOR);
        System.out.print("Wybierz odpowiednia cyfre: ");

    }

    public void start() {

        int option = 0;
        do {
            showMainMenu();
            option = ConsoleInput.getOptionFromRange(1, 4);
            ServiceContainer container = ServiceContainer.getInstance();

            switch (option) {
                case 1:
                    container.getTaskService().createTask();
                    break;

                case 2:
                    container.getTaskService().showAll();
                    break;

                case 3:
                    container.getTaskService().findAndUpdateTask();
                    break;

                default:
                    System.out.println("Zakonczono program");
            }

        } while (option != 4);
    }
}

