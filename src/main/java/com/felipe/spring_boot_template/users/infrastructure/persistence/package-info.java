/**
 * Adaptadores de <strong>persistencia</strong> del módulo Users.
 *
 * <p>
 * Agrupa las implementaciones concretas de persistencia basadas en JPA:
 * {@code JpaUserRepository} (interfaz Spring Data) y
 * {@code UserRepositoryAdapter} (adaptador que implementa el port de dominio).
 * Este sub-paquete es un detalle de implementación y no debe ser visible
 * fuera del módulo {@code users}.
 * </p>
 */
package com.felipe.spring_boot_template.users.infrastructure.persistence;
