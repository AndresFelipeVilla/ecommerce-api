/**
 * Capa de <strong>dominio</strong> del módulo Auth.
 *
 * <p>
 * Agrupa los objetos de dominio propios de la autenticación: peticiones
 * ({@code LoginRequest}, {@code RegisterRequest}) y respuestas de autenticación
 * ({@code AuthResponse}). Estas clases son internas al módulo {@code auth} y
 * <strong>no deben compartirse</strong> con otros módulos; si otro módulo
 * necesita datos de autenticación, debe hacerlo a través de {@code auth.api}.
 * </p>
 */
package com.felipe.spring_boot_template.auth.domain;
