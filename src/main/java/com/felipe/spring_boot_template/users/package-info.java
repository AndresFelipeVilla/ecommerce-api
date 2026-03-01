/**
 * Módulo <strong>Users</strong>.
 *
 * <p>
 * Gestiona el ciclo de vida de los usuarios de la plataforma: registro,
 * consulta y administración de cuentas. Su única superficie pública es el
 * subpaquete {@code users.api}; el resto de sub-paquetes son package-private
 * y no deben ser accedidos directamente desde otros módulos.
 * </p>
 *
 * <p>
 * Dependencias externas permitidas:
 * <ul>
 * <li>{@code shared} – tipos y abstracciones comunes de la plataforma.</li>
 * <li>{@code shared::api} – named interface pública de shared (BaseResponse,
 * etc.).</li>
 * <li>{@code exception} – jerarquía de excepciones de negocio.</li>
 * </ul>
 * </p>
 */
@ApplicationModule(allowedDependencies = { "shared", "shared::api", "exception" })
package com.felipe.spring_boot_template.users;

import org.springframework.modulith.ApplicationModule;
