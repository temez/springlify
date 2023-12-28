package dev.temez.springlify.commander.commons.execution.preprocessor.chain;

import dev.temez.springlify.commander.commons.exception.ConformableException;
import dev.temez.springlify.commander.commons.execution.CommandExecution;
import dev.temez.springlify.commander.commons.execution.preprocessor.ExecutionPreprocessor;
import org.jetbrains.annotations.NotNull;

/**
 * Interface for managing a chain of ExecutionPreprocessors to process command executions.
 *
 * @since 0.5.8.9dev
 */
public interface ExecutionPreprocessorChain {

  /**
   * Registers an ExecutionPreprocessor to the chain.
   *
   * @param executionPreprocessor The ExecutionPreprocessor to register.
   */
  void register(@NotNull ExecutionPreprocessor executionPreprocessor);

  /**
   * Processes the command execution through the registered ExecutionPreprocessors.
   *
   * @param execution The command execution context to be processed.
   * @throws ConformableException If an exception occurs during the preprocessing.
   */
  void process(@NotNull CommandExecution execution) throws ConformableException;
}
