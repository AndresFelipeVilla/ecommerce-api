/**
 * Capa de <strong>aplicación</strong> del módulo Auth.
 *
 * <p>
 * Contiene los servicios de autenticación ({@code AuthService}),
 * carga de usuarios para Spring Security ({@code AuthUserDetailsService})
 * y la lógica de generación/validación de JWT ({@code JwtService}).
 * Este paquete es <strong>package-private</strong> respecto al resto de la
 * plataforma: sus implementaciones son detalles internos del módulo
 * {@code auth} y no deben ser accedidos directamente desde fuera.
 * </p>
 */
package com.felipe.spring_boot_template.auth.application;
