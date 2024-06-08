package dev.temez.springlify.commander.service;

import dev.temez.springlify.commander.command.invocation.CommandInvocation;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Unmodifiable;

import java.util.List;

/**
 * Service interface for command-related operations.
 *
 * @since 0.7.0.0-RC1
 */
public interface CommandService {

  /**
   * Completes the command invocation based on the provided command invocation.
   *
   * @param commandInvocation The command invocation to complete.
   * @return An unmodifiable list of completion suggestions.
   */
  @NotNull @Unmodifiable List<String> complete(@NotNull CommandInvocation commandInvocation);

  /**
   * Executes the command invocation.
   *
   * @param commandInvocation The command invocation to execute.
   */
  void execute(@NotNull CommandInvocation commandInvocation);
}
