package dev.temez.springlify.commander.command.executor.provider;

import dev.temez.springlify.commander.command.sender.Sender;
import org.jetbrains.annotations.NotNull;

import java.lang.reflect.Parameter;

/**
 * A provider interface for parsing command parameters.
 * <p>
 * Implementations of this interface are responsible for parsing raw arguments into
 * appropriate parameter types based on the method parameter definition.
 * </p>
 *
 * @since 0.7.0.0-RC1
 */
public interface ParameterProvider {

  /**
   * Parses the raw argument into the appropriate parameter type.
   *
   * @param sender      The sender of the command.
   * @param rawArgument The raw argument to be parsed.
   * @param parameter   The method parameter definition.
   * @return The parsed parameter object.
   */
  @NotNull
  Object parse(@NotNull Sender<?> sender, @NotNull String rawArgument, @NotNull Parameter parameter);

  /**
   * Checks if the provider supports parsing for the given parameter.
   *
   * @param parameter The method parameter definition.
   * @return {@code true} if the provider supports parsing for the parameter, {@code false} otherwise.
   */
  boolean supports(@NotNull Parameter parameter);
}
