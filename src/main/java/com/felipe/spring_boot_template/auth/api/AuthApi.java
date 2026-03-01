package com.felipe.spring_boot_template.auth.api;

import java.util.Optional;

/**
 * API pública del módulo de autenticación.
 * Todos los tipos utilizados aquí pertenecen al paquete api de este módulo o al
 * JDK.
 * No se exponen tipos de Spring Security (UserDetails, etc.).
 */
public interface AuthApi {

    String authenticate(String email, String password);

    String register(String name, String email, String password);

    String extractUsername(String token);

    /**
     * Valida el token y devuelve la información del usuario si es válido.
     * Devuelve vacío si el token no es válido o ha expirado.
     */
    Optional<TokenValidationResult> validateToken(String token);
}
