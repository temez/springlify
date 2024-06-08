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
 * An {@link ParameterAdapter} implementation for parsing raw command parameters into {@code Integer} objects.
 * <p>
 * This adapter parses the raw command parameter into an {@code Integer} using {@link Integer#parseInt(String)}.
 * </p>
 *
 * @see ParameterAdapter
 * @since 0.7.0.0-RC1
 */
@Component
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public final class IntegerParameterAdapter implements ParameterAdapter<Integer> {

  /**
   * Parses the raw command parameter into an {@code Integer}.
   *
   * @param commandSender The command sender from which the raw argument was received.
   * @param rawArgument   The raw command parameter to parse.
   * @return The parsed {@code Integer}.
   * @throws ArgumentException If the raw argument cannot be parsed into an {@code Integer}.
   */
  @Override
  public @NotNull Integer parse(@NotNull Sender<?> commandSender, @NotNull String rawArgument) throws ArgumentException {
    try {
      return Integer.parseInt(rawArgument);
    } catch (NumberFormatException e) {
      throw new ArgumentException("commander.parameters.parameters-must-be-integer");
    }
  }
}

