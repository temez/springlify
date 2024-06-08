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
 * An {@link ParameterAdapter} implementation for parsing raw command parameters into {@code String} objects.
 * <p>
 * This adapter simply returns the raw command parameter as is, without any parsing.
 * </p>
 *
 * @see ParameterAdapter
 * @since 0.7.0.0-RC1
 */
@Component
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public final class StringParameterAdapter implements ParameterAdapter<String> {

  /**
   * Parses the raw command parameter into a {@code String}.
   *
   * @param commandSender The command sender from which the raw argument was received.
   * @param rawArgument   The raw command parameter to parse.
   * @return The parsed {@code String}.
   * @throws ArgumentException If an error occurs during parsing.
   */
  @Override
  public @NotNull String parse(@NotNull Sender<?> commandSender, @NotNull String rawArgument) throws ArgumentException {
    return rawArgument;
  }
}
