package com.infoshareacademy.core;

import java.util.InputMismatchException;
import java.util.Scanner;

public class ConsoleInput {


    public static int getInt(String textMessage, String errorMessage) {
        System.out.print(textMessage);
        int input = 0;
        boolean isValid = false;

        // repeat until input is not valid
        while (!isValid) {
            try {
                Scanner scanner = new Scanner(System.in);
                input = scanner.nextInt();
                isValid = true;
            } catch (InputMismatchException e) {
                System.out.println(errorMessage);
                System.out.print(textMessage);
            }

        }

        return input;
    }

    public static String getString(String textMessage, String errorMessage) {
        System.out.print(textMessage);
        String input = "";
        boolean isValid = false;

        // repeat until input is not valid
        while (!isValid) {
            try {
                Scanner scanner = new Scanner(System.in);
                input = scanner.nextLine();
                isValid = true;
            } catch (InputMismatchException e) {
                System.out.println(errorMessage);
                System.out.print(textMessage);
            }

        }

        return input;
    }

}

