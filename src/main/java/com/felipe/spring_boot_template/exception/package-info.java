/**
 * Módulo <strong>Exception</strong>.
 *
 * <p>
 * Define la jerarquía de excepciones de negocio de la plataforma
 * ({@code BusinessException}, {@code ResourceNotFoundException}) y el
 * manejador global de errores ({@code GlobalExceptionHandler}). Al igual
 * que {@code shared}, no depende de ningún otro módulo de negocio.
 * </p>
 *
 * <p>
 * {@code allowedDependencies} vacío garantiza que este paquete permanezca
 * libre de acoplamientos y pueda ser reutilizado por cualquier módulo.
 * </p>
 */
@ApplicationModule(allowedDependencies = {})
package com.felipe.spring_boot_template.exception;

import org.springframework.modulith.ApplicationModule;
