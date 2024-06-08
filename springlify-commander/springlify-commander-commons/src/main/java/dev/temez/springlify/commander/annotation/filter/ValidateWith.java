package dev.temez.springlify.commander.annotation.filter;

import dev.temez.springlify.commander.command.filter.CommandFilter;
import org.jetbrains.annotations.NotNull;

import java.lang.annotation.*;


/**
 * An annotation to specify validation filters for command annotations.
 * <p>
 * This annotation is used to specify a validation filter for command annotations. It can be applied to other annotations
 * to enforce validation rules on commands.
 * </p>
 * <p>
 * The {@code CommandFilter} type parameter specifies the type of validation filter to be applied.
 * </p>
 *
 * @see CommandFilter
 * @since 0.7.0.0-RC1
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(value = ElementType.ANNOTATION_TYPE)
public @interface ValidateWith {

  /**
   * Specifies the validation filter class to be applied.
   *
   * @return The class of the validation filter.
   */
  @NotNull
  Class<? extends CommandFilter<?>> value();
}
