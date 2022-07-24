package com.infoshareacademy.entity;

public enum Role {
    ROLE_ADMIN("Administrator"),
    ROLE_WORKER("Pracownik"),
    ROLE_CLIENT("Klient");

    private final String roleDescription;

    Role(String roleDescription) {
        this.roleDescription = roleDescription;
    }

    public String getRoleDescription() {
        return roleDescription;
    }
}
