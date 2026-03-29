package com.felipe.spring_boot_template.users;

/**
 * Command to create a new user.
 * Passed across module boundaries via
 * {@link UsersApi#createUser(CreateUserCommand)}.
 */
public record CreateUserCommand(
        String name,
        String email,
        String password,
        String role) {
}
