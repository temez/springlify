package dev.temez.springlify.commander.command.filter.provider;

import dev.temez.springlify.commander.command.Command;
import dev.temez.springlify.commander.command.sender.Sender;
import dev.temez.springlify.commander.exception.filter.CommandFilterException;
import org.jetbrains.annotations.NotNull;

/**
 * A provider interface for filtering commands before execution.
 * <p>
 * Implementations of this interface can determine whether a command is supported and apply filtering logic
 * based on various criteria such as the sender, the command itself, or any other contextual information.
 * </p>
 *
 * @since 0.7.0.0-RC1
 */
public interface CommandFilterProvider {

  /**
   * Checks if the provider supports the given command.
   *
   * @param command The command to check.
   * @return {@code true} if the provider supports the command, {@code false} otherwise.
   */
  boolean supports(@NotNull Command command);

  /**
   * Filters a command before its execution.
   * <p>
   * This method applies filtering logic to the given command based on the sender and any other relevant
   * contextual information. If the command is not allowed to execute, a {@link CommandFilterException} may be thrown.
   * </p>
   *
   * @param sender  The sender of the command.
   * @param command The command to filter.
   * @throws CommandFilterException If the command is not allowed to execute based on the applied filtering criteria.
   */
  void filter(@NotNull Sender<?> sender, @NotNull Command command) throws CommandFilterException;
}

