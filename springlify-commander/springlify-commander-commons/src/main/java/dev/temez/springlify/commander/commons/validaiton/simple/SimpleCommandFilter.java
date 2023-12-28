package dev.temez.springlify.commander.commons.validaiton.simple;

import dev.temez.springlify.commander.commons.command.RegisteredCommand;
import dev.temez.springlify.commander.commons.exception.ConformableException;
import dev.temez.springlify.commander.commons.sender.Sender;
import org.jetbrains.annotations.NotNull;

/**
 * Interface for defining simple command filters to be applied during command execution.
 *
 * @since 0.5.8.9dev
 */
public interface SimpleCommandFilter {

  /**
   * Applies the filter to the given command and sender.
   *
   * @param command The registered command being executed.
   * @param sender  The sender of the command.
   * @throws ConformableException If the filter encounters an issue and prevents command execution.
   */
  void filter(
      @NotNull RegisteredCommand command,
      @NotNull Sender<?> sender
  ) throws ConformableException;
}
