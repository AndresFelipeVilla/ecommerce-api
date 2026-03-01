/**
 * API pública del módulo <strong>Auth</strong>.
 *
 * <p>
 * Expone el contrato externo del módulo de autenticación: la interfaz
 * {@code AuthApi} y el resultado de validación de tokens
 * ({@code TokenValidationResult}). Otros módulos (p. ej. {@code config})
 * solo deben depender de este paquete, nunca de los paquetes internos
 * {@code application} o {@code domain}.
 * </p>
 *
 * <p>
 * Marcado con {@code @NamedInterface("api")} para que la dependencia
 * pueda declararse explícitamente como {@code "auth::api"}.
 * </p>
 */
@NamedInterface("api")
package com.felipe.spring_boot_template.auth.api;

import org.springframework.modulith.NamedInterface;
