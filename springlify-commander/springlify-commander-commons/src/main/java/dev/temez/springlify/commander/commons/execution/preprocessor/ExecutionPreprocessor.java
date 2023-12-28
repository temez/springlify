package dev.temez.springlify.commander.commons.execution.preprocessor;

import dev.temez.springlify.commander.commons.exception.ConformableException;
import dev.temez.springlify.commander.commons.execution.CommandExecution;
import org.jetbrains.annotations.NotNull;

/**
 * Interface for processing command executions before the actual execution of the command.
 *
 * @since 0.5.8.9dev
 */
public interface ExecutionPreprocessor {

  /**
   * Processes the given command execution before the actual execution of the command.
   *
   * @param execution The command execution context to be processed.
   * @throws ConformableException If an exception occurs during the preprocessing.
   */
  void process(@NotNull CommandExecution execution) throws ConformableException;
}
