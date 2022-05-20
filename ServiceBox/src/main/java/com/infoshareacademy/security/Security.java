package com.infoshareacademy.security;

import com.infoshareacademy.core.Input;
import com.infoshareacademy.model.User;

public class Security {
    public void login() {
        LoginAuthenticator authenticator = new LoginAuthenticator();
        boolean loggedIn = false;
        int failedAttempts = 0;

        System.out.println("------------------------------");
        System.out.println("Logowanie do systemu");
        System.out.println("------------------------------");
        
        while (!loggedIn) {
            // prompt for user login
            String username = Input.getString("Login: ");

            // prompt for user password
            String password = Input.getString("Hasło: ");

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
                    failedAttempts++;
                }
            } catch (Exception e) {
                // user not found
                System.out.println("Niepoprawny login lub hasło");
                failedAttempts++;
            }

            if (failedAttempts == 3) {
                System.out.println("Trzykrotnie wprowadzono błędne dane. Program zostanie zakończony.");
                System.exit(1);
            }
        }
    }

    public void logout() {

    }
}
