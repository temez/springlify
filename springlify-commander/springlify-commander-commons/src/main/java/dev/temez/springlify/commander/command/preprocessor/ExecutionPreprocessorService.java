package dev.temez.springlify.commander.command.preprocessor;

import dev.temez.springlify.commander.command.invocation.CommandInvocation;
import org.jetbrains.annotations.NotNull;

/**
 * Service interface for pre-processing command executions.
 * <p>
 * Implementations of this interface can perform pre-processing tasks on command invocations
 * before their execution, such as validation, normalization, or modification of command parameters.
 * </p>
 *
 * @since 0.7.0.0-RC1
 */
public interface ExecutionPreprocessorService {

  /**
   * Processes the given command invocation before execution.
   *
   * @param commandInvocation The command invocation to preprocess.
   */
  void process(@NotNull CommandInvocation commandInvocation);
}
