package dev.temez.springlify.commander.annotation.filter;

import dev.temez.springlify.commander.command.filter.impl.RequirePermissionFilter;
import org.jetbrains.annotations.NotNull;
import org.springframework.core.annotation.Order;

import java.lang.annotation.*;


/**
 * An annotation to specify a required permission for executing a command method.
 * <p>
 * This annotation is used to specify a required permission for executing a command method.
 * </p>
 * <p>
 * The {@code value} attribute specifies the required permission.
 * </p>
 *
 * @see RequirePermissionFilter
 * @since 0.7.0.0-RC1
 */
@Order()
@Documented
@ValidateWith(RequirePermissionFilter.class)
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface RequirePermission {

  /**
   * Specifies the required permission for executing the command method.
   *
   * @return The required permission.
   */
  @NotNull
  String value();
}
