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
 * A Spring component representing an ArgumentAdapter for the Integer type.
 * This adapter parses the raw argument as an Integer using Integer.parseInt.
 *
 * @since 0.5.8.9dev
 */
@Component
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public final class IntegerArgumentAdapter implements ArgumentAdapter<Integer> {

  /**
   * {@inheritDoc}
   */
  @Override
  public @NotNull Class<Integer> getTargetClass() {
    return Integer.class;
  }

  /**
   * Parses the raw argument into an Integer.
   *
   * @param commandSender The sender of the command.
   * @param rawArgument   The raw argument to be parsed.
   * @return The parsed Integer argument.
   * @throws ConformableException If there is an issue with conformance during parsing,
   *                              such as if the argument cannot be converted to an Integer.
   */
  @Override
  public @NotNull Integer parse(
      @NotNull Sender<?> commandSender,
      @NotNull String rawArgument
  ) throws ConformableException {
    try {
      return Integer.parseInt(rawArgument);
    } catch (Exception e) {
      throw new ConformableException("commander.arguments.argument-must-be-integer");
    }
  }
}
