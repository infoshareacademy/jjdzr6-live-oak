package com.infoshareacademy.entity.user;

public enum UserRole {
    ADMIN("Administrator"),
    EMPLOYEE("Pracownik"),
    CLIENT("Klient");

    private final String roleDescription;

    UserRole(String roleDescription) {
        this.roleDescription = roleDescription;
    }

    public String getRoleDescription() {
        return roleDescription;
    }

    public String getRoleNameWithPrefix() {
        return "ROLE_" + name();
    }
}
