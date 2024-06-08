package dev.temez.springlify.commander.command.filter;

import dev.temez.springlify.commander.command.Command;
import dev.temez.springlify.commander.command.sender.Sender;
import dev.temez.springlify.commander.exception.filter.CommandFilterException;
import org.jetbrains.annotations.NotNull;

/**
 * Service interface for command filtering operations.
 *
 * @since 0.7.0.0-RC1
 */
public interface CommandFilterService {

  /**
   * Filters the command execution based on the sender and the command.
   *
   * @param sender  The sender executing the command.
   * @param command The command being executed.
   * @throws CommandFilterException If an error occurs during the filtering process.
   */
  void filter(@NotNull Sender<?> sender, @NotNull Command command) throws CommandFilterException;

  /**
   * Checks if the sender has access to execute the given command.
   *
   * @param sender  The sender executing the command.
   * @param command The command to check accessibility for.
   * @return {@code true} if the sender has access to execute the command, {@code false} otherwise.
   */
  boolean isAccessible(@NotNull Sender<?> sender, @NotNull Command command);
}
