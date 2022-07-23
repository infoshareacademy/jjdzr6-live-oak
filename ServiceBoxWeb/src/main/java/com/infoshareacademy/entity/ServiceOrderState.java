package com.infoshareacademy.entity;

public enum ServiceOrderState {
    CREATED("Nowe zlecenie"),
    IN_PROGRESS("W trakcie realizacji"),
    FINISHED("Zlecenie zamknięte");

    private final String description;

    ServiceOrderState(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
