/**
 * Módulo <strong>Config</strong>.
 *
 * <p>
 * Alberga la configuración técnica transversal de la plataforma:
 * {@code SecurityConfig} y {@code JwtAuthenticationFilter}. Necesita acceder
 * al módulo {@code auth} para integrar la cadena de filtros de Spring Security
 * con la lógica de JWT.
 * </p>
 *
 * <p>
 * Dependencias externas permitidas:
 * <ul>
 * <li>{@code auth::api} – contrato público para validar tokens JWT.</li>
 * </ul>
 * </p>
 */
@ApplicationModule(allowedDependencies = { "auth::api" })
package com.felipe.spring_boot_template.config;

import org.springframework.modulith.ApplicationModule;
