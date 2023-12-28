package dev.temez.springlify.commander.commons.annotation.help;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import org.jetbrains.annotations.NotNull;

/**
 * An annotation indicating a execution hint for a method parameter.
 * This annotation is intended to be used on method parameters.
 *
 * @since 0.5.8.9dev
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.PARAMETER)
public @interface Hint {

  /**
   * The hint value for the annotated parameter.
   *
   * @return The hint value.
   */
  @NotNull String value();
}
