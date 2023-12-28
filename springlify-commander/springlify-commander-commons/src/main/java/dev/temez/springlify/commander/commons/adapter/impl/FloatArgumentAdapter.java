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
 * A Spring component representing an ArgumentAdapter for the Float type.
 * This adapter parses the raw argument as a Float using Float.parseFloat.
 *
 * @since 0.5.8.9dev
 */
@Component
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public final class FloatArgumentAdapter implements ArgumentAdapter<Float> {

  /**
   * {@inheritDoc}
   */
  @Override
  public @NotNull Class<Float> getTargetClass() {
    return Float.class;
  }

  /**
   * Parses the raw argument into a Float.
   *
   * @param commandSender The sender of the command.
   * @param rawArgument   The raw argument to be parsed.
   * @return The parsed Float argument.
   * @throws ConformableException If there is an issue with conformance during parsing,
   *                              such as if the argument cannot be converted to a Float.
   */
  @Override
  public @NotNull Float parse(
      @NotNull Sender<?> commandSender,
      @NotNull String rawArgument
  ) throws ConformableException {
    try {
      return Float.parseFloat(rawArgument);
    } catch (Exception e) {
      throw new ConformableException("commander.arguments.argument-must-be-float");
    }
  }
}
