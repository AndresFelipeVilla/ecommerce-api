package com.felipe.spring_boot_template.users.api;

import java.util.Optional;

/**
 * Public API of the Users module.
 * Other modules (e.g., auth) must interact with users exclusively through this
 * interface.
 */
public interface UsersApi {

    UserResponse findByEmail(String email);

    Optional<UserResponse> findByEmailOptional(String email);

    UserResponse createUser(String name, String email, String password, String role);

    boolean existsByEmail(String email);
}
