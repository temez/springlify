package dev.temez.springlify.commander.commons.executor;

import dev.temez.springlify.commander.commons.exception.ConformableException;
import dev.temez.springlify.commander.commons.execution.CommandExecution;
import org.jetbrains.annotations.NotNull;

/**
 * Interface for executing commands.
 *
 * @since 0.5.8.9dev
 */
public interface CommandExecutor {

  /**
   * Executes the command based on the provided execution context.
   *
   * @param commandExecution The context of the command execution.
   * @throws ConformableException If an exception occurs during command execution.
   */
  void execute(@NotNull CommandExecution commandExecution) throws ConformableException;

}
