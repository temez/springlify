package dev.temez.springlify.commander.command.executor;

import dev.temez.springlify.commander.command.invocation.CommandInvocation;
import dev.temez.springlify.commander.exception.CommandExecutionException;
import org.jetbrains.annotations.NotNull;

/**
 * An interface representing a command executor responsible for executing commands.
 * <p>
 * Implementations of this interface handle the execution logic for commands based on the provided {@link CommandInvocation}.
 * </p>
 *
 * @since 0.7.0.0-RC1
 */
public interface CommandExecutor {

  /**
   * Executes a command based on the provided {@link CommandInvocation}.
   * <p>
   * This method processes the command invocation and performs the necessary actions defined by the command.
   * Implementations should handle any exceptions that occur during execution.
   * </p>
   *
   * @param commandInvocation The command invocation containing the details of the command to be executed.
   * @throws CommandExecutionException If an error occurs during command execution.
   */
  void execute(@NotNull CommandInvocation commandInvocation) throws CommandExecutionException;

}
