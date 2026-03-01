package com.felipe.spring_boot_template.auth.api;

import java.util.List;

/**
 * Resultado devuelto por el módulo de autenticación al validar un token JWT.
 * Contiene todo lo que el filtro de seguridad necesita para construir el
 * SecurityContext sin filtrar información interna de Spring Security
 * (UserDetails)
 * a través de la API del módulo.
 */
public record TokenValidationResult(
                String username,
                List<String> roles) {
}
