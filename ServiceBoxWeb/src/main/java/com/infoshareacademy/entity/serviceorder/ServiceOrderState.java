package com.infoshareacademy.entity.serviceorder;

public enum ServiceOrderState {
    CREATED("Nowe zlecenie"),
    WAITING_FOR_PARTS("Oczekiwanie na części"),
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
