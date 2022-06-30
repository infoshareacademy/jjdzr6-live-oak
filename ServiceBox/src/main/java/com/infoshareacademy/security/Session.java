package com.infoshareacademy.security;

import com.infoshareacademy.model.User;

public class Session {
    public static User user;

    public static User getUser() {
        return user;
    }

    public static void setUser(User user) {
        Session.user = user;
    }
}
