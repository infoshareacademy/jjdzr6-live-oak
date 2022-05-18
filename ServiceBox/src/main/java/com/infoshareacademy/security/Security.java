package com.infoshareacademy.security;

import com.infoshareacademy.model.User;

import java.util.Scanner;

public class Security {
    public void login() {
        LoginAuthenticator authenticator = new LoginAuthenticator();
        boolean loggedIn = false;

        while (!loggedIn) {
            Scanner scanner = new Scanner(System.in);

            // prompt for user login
            System.out.print("Login: ");
            String username = scanner.nextLine();

            // prompt for user password
            System.out.print("Hasło: ");
            String password = scanner.nextLine();

            try {
                // search for user
                User user = authenticator.getUser(username);

                // user exists - check password
                if (authenticator.checkUserPassword(user, password)) {
                    Session.setUser(user);
                    loggedIn = true;
                } else {
                    // wrong password
                    System.out.println("Niepoprawny login lub hasło");
                }
            } catch (Exception e) {
                // user not found
                System.out.println("Niepoprawny login lub hasło");
            }
        }
    }

    public void logout() {

    }
}
