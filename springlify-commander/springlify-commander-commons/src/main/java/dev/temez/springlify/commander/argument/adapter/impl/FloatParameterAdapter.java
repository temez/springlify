package dev.temez.springlify.commander.argument.adapter.impl;

import dev.temez.springlify.commander.argument.adapter.ParameterAdapter;
import dev.temez.springlify.commander.command.sender.Sender;
import dev.temez.springlify.commander.exception.argument.ArgumentException;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Component;

/**
 * An {@link ParameterAdapter} implementation for parsing raw command parameters into {@code Float} objects.
 * <p>
 * This adapter parses the raw command parameter into a {@code Float} using {@link Float#parseFloat(String)}.
 * </p>
 *
 * @see ParameterAdapter
 * @since 0.7.0.0-RC1
 */
@Component
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class FloatParameterAdapter implements ParameterAdapter<Float> {

  /**
   * Parses the raw command parameter into a {@code Float}.
   *
   * @param commandSender The command sender from which the raw argument was received.
   * @param rawArgument   The raw command parameter to parse.
   * @return The parsed {@code Float}.
   * @throws ArgumentException If the raw argument cannot be parsed into a {@code Float}.
   */
  @Override
  public @NotNull Float parse(@NotNull Sender<?> commandSender, @NotNull String rawArgument) throws ArgumentException {
    try {
      return Float.parseFloat(rawArgument);
    } catch (NumberFormatException e) {
      throw new ArgumentException("commander.parameters.parameters-must-be-float");
    }
  }
}
