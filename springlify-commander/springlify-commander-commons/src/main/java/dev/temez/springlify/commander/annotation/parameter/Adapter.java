package dev.temez.springlify.commander.annotation.parameter;

import dev.temez.springlify.commander.argument.adapter.ParameterAdapter;
import org.jetbrains.annotations.NotNull;

import java.lang.annotation.*;

/**
 * An annotation used to specify an external parameter adapter for a method parameter.
 * <p>
 * This annotation should be applied to method parameters in command methods to specify the adapter that will
 * convert the raw input string into the appropriate parameter type.
 * </p>
 *
 * @since 0.7.0.0-RC1
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.PARAMETER)
public @interface Adapter {

  /**
   * The class of the argument adapter to be used for this parameter.
   *
   * @return The class of the argument adapter.
   */
  @NotNull Class<? extends ParameterAdapter<?>> value();
}
