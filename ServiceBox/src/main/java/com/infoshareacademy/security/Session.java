package com.infoshareacademy.security;

public class Session {
    private static int lastId = 0;

    public static int generateId() {
        lastId++;
        return lastId;
    }
}
