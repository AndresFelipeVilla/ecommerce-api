/**
 * API pública del módulo <strong>Users</strong>.
 *
 * <p>
 * Este paquete es la <em>única</em> superficie visible desde otros módulos.
 * Expone exclusivamente interfaces y DTOs de solo lectura (p. ej.
 * {@code UsersApi}, {@code UserResponse}) que permiten a módulos externos
 * operar sobre usuarios sin conocer los detalles de implementación.
 * </p>
 *
 * <p>
 * Marcado con {@code @NamedInterface("api")} para que Spring Modulith
 * lo identifique como el contrato exportado del módulo y otros módulos puedan
 * declarar la dependencia explícita {@code "users::api"}.
 * </p>
 */
@NamedInterface("api")
package com.felipe.spring_boot_template.users.api;

import org.springframework.modulith.NamedInterface;
