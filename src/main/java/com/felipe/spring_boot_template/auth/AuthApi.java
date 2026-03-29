package com.felipe.spring_boot_template.auth;

import java.util.Optional;

public interface AuthApi {

    String authenticate(String email, String password);

    String register(String name, String email, String password);

    String extractUsername(String token);

    Optional<TokenValidationResult> validateToken(String token);
}
