/**
 * Capa de <strong>dominio</strong> del módulo Shared.
 *
 * <p>
 * Contiene las abstracciones de dominio transversales a toda la plataforma,
 * en particular {@code BaseEntity} (clase base JPA con campos de auditoría).
 * Aunque forma parte del shared kernel, su uso debe limitarse a las capas de
 * dominio de cada módulo; los detalles de persistencia son responsabilidad
 * de cada módulo en su propia capa de infraestructura.
 * </p>
 */
package com.felipe.spring_boot_template.shared.domain;
