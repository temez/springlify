package dev.temez.springlify.commander.commons.validaiton.annotation;

import dev.temez.springlify.commander.commons.annotation.validation.ValidateWith;
import dev.temez.springlify.commander.commons.validaiton.RequirePermissionFilter;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import org.jetbrains.annotations.NotNull;
import org.springframework.core.annotation.Order;

/**
 * Annotation indicating that a method requires a specific permission.
 *
 * <p>This annotation is processed by the {@link RequirePermissionFilter}
 * to enforce permission checks.</p>
 *
 * @since 0.5.8.9dev
 */
@Order(0)
@ValidateWith(RequirePermissionFilter.class)
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface RequirePermission {

  /**
   * The required permission.
   *
   * @return The required permission.
   */
  @NotNull String value();
}
