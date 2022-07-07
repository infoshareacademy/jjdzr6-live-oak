package com.infoshareacademy.core;

import java.time.LocalDate;
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

    public static float getFloat(String prompt) {
        System.out.print(prompt);
        float input = 0;
        boolean isValid = false;

        // repeat until input is not valid
        while (!isValid) {
            try {
                Scanner scanner = new Scanner(System.in);
                input = scanner.nextFloat();
                isValid = true;
            } catch (InputMismatchException e) {
                ConsoleOutput.alert(ERROR_MESSAGE);
                System.out.print(prompt);
            }
        }

        return input;
    }

    public static int getYear(String prompt) {
        boolean isValid = false;
        int year = 0;

        while (!isValid) {
            year = getInt(prompt);

            if (year > LocalDate.now().getYear() || year < 1900) {
                System.out.println("Niepoprawny rok. Wprowadz wartosc z zakresu 1900-" + LocalDate.now().getYear());
            } else {
                isValid = true;

            }
        }

        return year;
    }

    public static float getCapacity(String prompt) {
        boolean isValid = false;
        float capacity = 0;

        while (!isValid) {
            capacity = getFloat(prompt);

            if (capacity < 0) {
                System.out.println("Pojemnosc silnika musi byc wieksza od 0");
            } else {
                isValid = true;

            }
        }

        return capacity;
    }

    public static String getEmail(String prompt) {
        boolean isValid = false;
        String email = null;

        while (!isValid) {
            email = getString(prompt);

            if (email.contains("@")) {
                isValid = true;
            } else {
                System.out.println("Adres e-mail musi zawierać znak: @");
            }
        }
        return email;
    }

    public static void waitForEnter() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Nacisnij enter, aby kontynuowac...");
        scanner.nextLine();
    }
}

