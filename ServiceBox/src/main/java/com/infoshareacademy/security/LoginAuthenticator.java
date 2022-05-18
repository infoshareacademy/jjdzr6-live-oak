package com.infoshareacademy.security;

import com.infoshareacademy.model.User;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

public class LoginAuthenticator {
    public User getUser(String username) throws Exception {
        Path path = Paths.get("src", "main", "resources", "user.csv");

        try {
            Scanner scanner = new Scanner(path);

            // search for user
            while (scanner.hasNextLine()) {
                String row = scanner.nextLine();
                String[] values = row.split(",");

                /*
                 * values[0] = id
                 * values[1] = username
                 * values[2] = password
                 * */

                if (username.equals(values[1])) {
                    // user found
                    return new User(Integer.parseInt(values[0]), values[1], values[2]);
                }
            }
        } catch (IOException e) {
            throw new Exception("User not found");
        }

        // not found
        throw new Exception("User not found");
    }

    public boolean checkUserPassword(User user, String password) {
        return user.getPassword().equals(password);
    }
}
