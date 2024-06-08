package dev.temez.springlify.commander.annotation.parameter;

import dev.temez.springlify.commander.argument.completer.ParameterCompleter;
import org.jetbrains.annotations.NotNull;

import java.lang.annotation.*;

/**
 * An annotation used to specify an external parameter completer for a method parameter.
 * <p>
 * This annotation should be applied to method parameters in command methods to specify the completer that will
 * provide completion suggestions for the parameter.
 * </p>
 *
 * @since 0.7.0.0-RC1
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.PARAMETER)
public @interface Completer {

  /**
   * The class of the argument completer to be used for this parameter.
   *
   * @return The class of the argument completer.
   */
  @NotNull Class<? extends ParameterCompleter> value();
}
