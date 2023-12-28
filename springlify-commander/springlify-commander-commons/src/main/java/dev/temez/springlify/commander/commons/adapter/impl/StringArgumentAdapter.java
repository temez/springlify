package dev.temez.springlify.commander.commons.adapter.impl;

import dev.temez.springlify.commander.commons.adapter.ArgumentAdapter;
import dev.temez.springlify.commander.commons.exception.ConformableException;
import dev.temez.springlify.commander.commons.sender.Sender;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Component;

/**
 * A Spring component representing an ArgumentAdapter for the String type.
 * This adapter simply returns the raw argument as-is when parsing.
 *
 * @since 0.5.8.9dev
 */
@Component
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public final class StringArgumentAdapter implements ArgumentAdapter<String> {

  /**
   * {@inheritDoc}
   */
  @Override
  public @NotNull Class<String> getTargetClass() {
    return String.class;
  }

  /**
   * Parses the raw argument and returns it as a String.
   *
   * @param commandSender The sender of the command.
   * @param rawArgument   The raw argument to be parsed.
   * @return The parsed String argument.
   * @throws ConformableException If there is an issue with conformance during parsing.
   */
  @Override
  public @NotNull String parse(
      @NotNull Sender<?> commandSender,
      @NotNull String rawArgument
  ) throws ConformableException {
    return rawArgument;
  }
}
