/**
 * Módulo <strong>Auth</strong>.
 *
 * <p>
 * Responsable de la autenticación y autorización de la plataforma:
 * login, registro, generación y validación de JWT, y configuración de
 * Spring Security. Accede al módulo {@code users} <em>únicamente</em> a
 * través de su interfaz pública {@code users::api}.
 * </p>
 *
 * <p>
 * Dependencias externas permitidas:
 * <ul>
 * <li>{@code users::api} – contrato público para consultar usuarios.</li>
 * <li>{@code shared} – tipos y abstracciones comunes de la plataforma.</li>
 * <li>{@code shared::api} – named interface pública de shared (BaseResponse,
 * etc.).</li>
 * <li>{@code exception} – jerarquía de excepciones de negocio.</li>
 * </ul>
 * </p>
 */
@ApplicationModule(allowedDependencies = { "users::api", "shared", "shared::api", "exception" })
package com.felipe.spring_boot_template.auth;

import org.springframework.modulith.ApplicationModule;
