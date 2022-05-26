package com.infoshareacademy.menu;

import com.infoshareacademy.core.ServiceContainer;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Menu {
    private final ServiceContainer container = new ServiceContainer();

    public ServiceContainer getContainer() {
        return container;
    }

    private void showMainMenu() {
        System.out.println("      Witamy w systemie ServiceBox      ");
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        System.out.println("1. Utworz nowe zlecenie naprawy");
        System.out.println("2. Wyswietl wszystkie zlecenia naprawy");
        System.out.println("3. Wyszukaj zlecenie naprawy");
        System.out.println("4. Zakoncz");
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        System.out.print("Wybierz odpowiednia cyfre: ");
    }

    private int getOptionFromRange(int min, int max) {
        int selectedOption = 0;
        boolean isValid = false;

        // repeat until input is not valid
        while (!isValid) {
            try {
                Scanner scanner = new Scanner(System.in);
                selectedOption = scanner.nextInt();

                // check if in range
                if (selectedOption >= min && selectedOption <= max) {
                    isValid = true;
                } else {
                    // out of range
                    System.out.print("Wybierz opcje z zakresu " + min + "-" + max + ": ");
                }
            } catch (InputMismatchException e) {
                System.out.print("Niepoprawa wartosc, sprobuj ponownie:  ");
            }
        }

        return selectedOption;
    }

    public void start() {
        showMainMenu();

        switch (getOptionFromRange(1, 4)) {
            case 1:
                getContainer().getTaskService().createTask();
                break;

            case 2:
                getContainer().getTaskService().showAll();
                break;

            case 3:
                getContainer().getTaskService().showSingleTask();
                break;

            default:
                System.out.println("Zakonczono program");
        }
    }
}
