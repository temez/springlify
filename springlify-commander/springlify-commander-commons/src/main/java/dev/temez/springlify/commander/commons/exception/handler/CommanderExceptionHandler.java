package dev.temez.springlify.commander.commons.exception.handler;

import dev.temez.springlify.commander.commons.exception.ConformableException;
import dev.temez.springlify.commander.commons.execution.CommandExecution;
import org.jetbrains.annotations.NotNull;

/**
 * Interface for handling exceptions that occur during command execution.
 *
 * @since 0.5.8.9dev
 */
public interface CommanderExceptionHandler {

  /**
   * Handles the given exception that occurred during command execution.
   *
   * @param execution The command execution context.
   * @param exception The exception that occurred.
   */
  void handle(@NotNull CommandExecution execution, @NotNull ConformableException exception);
}
