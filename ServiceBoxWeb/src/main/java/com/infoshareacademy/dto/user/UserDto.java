package com.infoshareacademy.dto.user;

import com.infoshareacademy.entity.user.Role;
import com.infoshareacademy.entity.user.User;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Set;
import java.util.stream.Collectors;

/**
 * A DTO for the {@link User} entity
 */
@Getter
@AllArgsConstructor
public class UserDto {
    private final Long id;
    private final String username;
    private final boolean enabled;
    private final boolean initialPassword;
    private final Set<String> roles;

    public static UserDto fromUser(User user) {
        if (user == null) {
            return null;
        }

        return new UserDto(
                user.getId(),
                user.getUsername(),
                user.isEnabled(),
                user.isInitialPassword(),
                user.getRoles().stream()
                        .map(Role::getName)
                        .collect(Collectors.toSet())
        );
    }
}