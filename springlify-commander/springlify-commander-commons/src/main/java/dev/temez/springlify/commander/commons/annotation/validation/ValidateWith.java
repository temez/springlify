package dev.temez.springlify.commander.commons.annotation.validation;

import dev.temez.springlify.commander.commons.validaiton.CommandFilter;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import org.jetbrains.annotations.NotNull;

/**
 * An annotation indicating the validator to be applied to a command.
 * This annotation is intended to be used on other annotations.
 *
 * @since 0.5.8.9dev
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(value = ElementType.ANNOTATION_TYPE)
public @interface ValidateWith {

  /**
   * The validator class to be applied.
   *
   * @return The validator class.
   */
  @NotNull Class<? extends CommandFilter<?>> value();
}
