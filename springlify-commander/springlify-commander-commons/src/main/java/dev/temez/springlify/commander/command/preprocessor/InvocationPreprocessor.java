package dev.temez.springlify.commander.command.preprocessor;

import dev.temez.springlify.commander.command.invocation.CommandInvocation;
import org.jetbrains.annotations.NotNull;

/**
 * Interface for pre-processing command invocations before execution.
 * <p>
 * Implementations of this interface can perform pre-processing tasks on command invocations
 * such as validation, normalization, or modification of command parameters.
 * </p>
 *
 * @since 0.7.0.0-RC1
 */
public interface InvocationPreprocessor {

  /**
   * Processes the given command invocation before execution.
   *
   * @param invocation The command invocation to preprocess.
   */
  void process(@NotNull CommandInvocation invocation);
}
