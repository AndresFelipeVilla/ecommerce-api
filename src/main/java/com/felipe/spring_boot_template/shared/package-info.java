/**
 * Módulo <strong>Shared / Shared Kernel</strong>.
 *
 * <p>
 * Contiene abstracciones, tipos de valor y utilidades que son transversales
 * a toda la plataforma (p. ej. {@code BaseEntity}, {@code BaseResponse}).
 * No depende de ningún otro módulo de negocio; es el cimiento sobre el que
 * construyen el resto de módulos.
 * </p>
 *
 * <p>
 * Al no tener dependencias de negocio, {@code allowedDependencies} está
 * vacío intencionalmente para reforzar que este módulo permanezca puro.
 * </p>
 */
@ApplicationModule(allowedDependencies = {})
package com.felipe.spring_boot_template.shared;

import org.springframework.modulith.ApplicationModule;
