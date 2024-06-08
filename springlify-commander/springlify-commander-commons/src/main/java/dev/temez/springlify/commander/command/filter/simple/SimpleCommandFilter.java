package dev.temez.springlify.commander.command.filter.simple;

import dev.temez.springlify.commander.command.Command;
import dev.temez.springlify.commander.command.sender.Sender;
import dev.temez.springlify.commander.exception.filter.CommandFilterException;
import org.jetbrains.annotations.NotNull;

/**
 * Interface for implementing simple command filters.
 *
 * @since 0.7.0.0-RC1
 */
public interface SimpleCommandFilter {

  /**
   * Performs filtering on the command execution.
   *
   * @param sender  The sender executing the command.
   * @param command The command being executed.
   * @throws CommandFilterException If an error occurs during the filtering process.
   */
  void doFilter(@NotNull Sender<?> sender, @NotNull Command command) throws CommandFilterException;
}
