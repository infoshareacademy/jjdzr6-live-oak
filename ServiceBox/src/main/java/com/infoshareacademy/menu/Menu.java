package com.infoshareacademy.menu;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Menu {

    private void showMenu() {

        System.out.println("      Witamy w systemie ServiceBox      ");
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        System.out.println("1. Utworz nowe zlecenie naprawy");
        System.out.println("2. Wyswietl wszystkie zlecenia naprawy");
        System.out.println("3. Wyszukaj zlecenie naprawy");
        System.out.println("4. Zakoncz");
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        System.out.print("Wybierz odpowiednia cyfre:  ");
    }

    private int getOption() {
        int option = 0;
        boolean isValid = false;
        while (isValid == false) {
            try {
                Scanner scanner = new Scanner(System.in);
                option = scanner.nextInt();
                if (option >= 1 && option <= 4) {
                    isValid = true;
                }else {
                    System.out.print("Wybierz opcje z zakresu 1-4:  ");
                }


            } catch (InputMismatchException e) {
                System.out.print("Wybrales zla opcje, sprobuj ponownie:  ");

            }
        }


        return option;
    }

    public void start(){

        showMenu();
        int option = getOption();
        switch(option) {

            case 1: System.out.println("Wybrano opcje 1");
            break;

            case 2: System.out.println("Wybrano opcje 2");
            break;

            case 3: System.out.println("Wybrano opcje 3");
            break;

            default: System.out.print("Zakonczono program");

        }




    }
}
