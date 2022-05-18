package com.infoshareacademy.security;

import com.infoshareacademy.model.User;

public class Session {
    private static User user;

    public static User getUser() {
        return user;
    }

    public static void setUser(User user) {
        Session.user = user;
    }
}
