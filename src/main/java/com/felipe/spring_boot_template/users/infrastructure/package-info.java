/**
 * Capa de <strong>infraestructura</strong> del módulo Users.
 *
 * <p>
 * Implementa los ports del dominio con adaptadores concretos de
 * persistencia (Spring Data JPA). Contiene los adaptadores de repositorio
 * ({@code UserRepositoryAdapter}, {@code JpaUserRepository}).
 * </p>
 *
 * <p>
 * Este paquete es <strong>package-private</strong>: los detalles de
 * implementación de persistencia no deben filtrarse hacia otros módulos ni
 * hacia las capas superiores fuera del módulo {@code users}.
 * </p>
 */
package com.felipe.spring_boot_template.users.infrastructure;
