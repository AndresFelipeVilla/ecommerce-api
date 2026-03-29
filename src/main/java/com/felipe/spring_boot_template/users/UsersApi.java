package com.felipe.spring_boot_template.users;

import java.util.Optional;

/**
 * Public API of the Users module.
 * Other modules (e.g., auth) must interact with users exclusively through this
 * interface.
 */
public interface UsersApi {

    UserResponse findByEmail(String email);

    Optional<UserResponse> findByEmailOptional(String email);

    UserResponse createUser(CreateUserCommand command);

    boolean existsByEmail(String email);
}
