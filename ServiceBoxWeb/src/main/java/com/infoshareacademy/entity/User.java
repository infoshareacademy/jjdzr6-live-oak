package com.infoshareacademy.entity;

import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
public class User extends Entity {
    private String username;
    private String password;
    private Set<Role> roles;

    public User() {
    }

    public User(String username, String password) {
        this.username = username;
        this.password = password;

        roles.add(Role.ROLE_CLIENT);
    }
}
