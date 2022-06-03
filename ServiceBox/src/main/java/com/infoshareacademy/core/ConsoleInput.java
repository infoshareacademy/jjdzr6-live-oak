package com.infoshareacademy.core;

import java.util.InputMismatchException;
import java.util.Scanner;

public class ConsoleInput {
    private static final String ERROR_MESSAGE = "Niepoprawna wartosc";

    public static int getInt(String prompt) {
        System.out.print(prompt);
        int input = 0;
        boolean isValid = false;

        // repeat until input is not valid
        while (!isValid) {
            try {
                Scanner scanner = new Scanner(System.in);
                input = scanner.nextInt();
                isValid = true;
            } catch (InputMismatchException e) {
                ConsoleOutput.alert(ERROR_MESSAGE);
                System.out.print(prompt);
            }
        }

        return input;
    }

    public static String getString(String prompt) {
        System.out.print(prompt);
        String input = "";
        boolean isValid = false;

        // repeat until input is not valid
        while (!isValid) {
            try {
                Scanner scanner = new Scanner(System.in);
                input = scanner.nextLine();

                if (!input.trim().isEmpty()) {
                    // is valid if not empty string
                    isValid = true;
                } else {
                    ConsoleOutput.alert("Zadne dane nie zostaly wprowadzone");
                    System.out.print(prompt);
                }
            } catch (InputMismatchException e) {
                ConsoleOutput.alert(ERROR_MESSAGE);
                System.out.print(prompt);
            }
        }

        return input;
    }

    public static int getIntFromRange(String prompt, int min, int max) {
        System.out.print(prompt);
        int input = 0;
        boolean isValid = false;

        // repeat until input is not valid
        while (!isValid) {
            try {
                Scanner scanner = new Scanner(System.in);
                input = scanner.nextInt();

                // check if in range
                if (input >= min && input <= max) {
                    isValid = true;
                } else {
                    // out of range
                    ConsoleOutput.alert("Wybrano niepoprawną opcje. Wybierz opcje z zakresu " + min + "-" + max );
                    System.out.print(prompt);
                }
            } catch (InputMismatchException e) {
                ConsoleOutput.alert(ERROR_MESSAGE);
                System.out.print(prompt);
            }
        }

        return input;
    }

    public static void waitForEnter() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Nacisnij enter, aby kontynuowac...");
        scanner.nextLine();
    }
}

