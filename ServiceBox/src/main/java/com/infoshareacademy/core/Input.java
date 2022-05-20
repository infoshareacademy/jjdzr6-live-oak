package com.infoshareacademy.core;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Input {
    public static String getString(String prompt) {
        System.out.print(prompt);

        String input = "";
        boolean isValid = false;

        while (!isValid) {
            try {
                Scanner sc = new Scanner(System.in);
                input = sc.nextLine();
                isValid = true;
            } catch (InputMismatchException e) {
                System.out.println("Niepoprawna wartość.");
            }
        }

        return input;
    }

    public static int getInt(String prompt) {
        System.out.print(prompt);

        int input = 0;
        boolean isValid = false;

        while (!isValid) {
            try {
                Scanner sc = new Scanner(System.in);
                input = sc.nextInt();
                isValid = true;
            } catch (InputMismatchException e) {
                System.out.println("Niepoprawna wartość.");
            }
        }

        return input;
    }

    public static double getDouble(String prompt) {
        System.out.print(prompt);

        double input = 0.0;
        boolean isValid = false;

        while (!isValid) {
            try {
                Scanner sc = new Scanner(System.in);
                input = sc.nextDouble();
                isValid = true;
            } catch (InputMismatchException e) {
                System.out.println("Niepoprawna wartość.");
            }
        }

        return input;
    }

    public static String getYesOrNo(String prompt) {
        System.out.println(prompt);
        System.out.print("[T]ak/[N]ie: ");

        String input = "";
        boolean isValid = false;

        while (!isValid) {
            try {
                Scanner sc = new Scanner(System.in);
                input = sc.nextLine().trim().toUpperCase();

                if (input.equals("T") || input.equals("N")) {
                    isValid = true;
                } else {
                    System.out.println("Niepoprawna wartość. Wprowadź T lub N.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Niepoprawna wartość.");
            }
        }

        return input;
    }
}
