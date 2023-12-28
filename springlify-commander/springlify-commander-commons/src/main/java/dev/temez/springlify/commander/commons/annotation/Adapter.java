package dev.temez.springlify.commander.commons.annotation;

import dev.temez.springlify.commander.commons.adapter.ArgumentAdapter;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import org.jetbrains.annotations.NotNull;

/**
 * An annotation indicating the adapter to be applied to a method parameter.
 * This annotation is intended to be used on method parameters.
 *
 * @since 0.5.8.9dev
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.PARAMETER)
public @interface Adapter {

  /**
   * The adapter class to be applied.
   *
   * @return The adapter class.
   */
  @NotNull Class<? extends ArgumentAdapter<?>> value();
}
