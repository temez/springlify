package dev.temez.springlify.commander.command.preprocessor.impl;

import dev.temez.springlify.commander.command.Command;
import dev.temez.springlify.commander.command.invocation.CommandInvocation;
import dev.temez.springlify.commander.command.preprocessor.InvocationPreprocessor;
import dev.temez.springlify.commander.exception.CommandException;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.Comparator;

/**
 * Preprocessor for handling subcommands within command invocations.
 * <p>
 * This preprocessor extracts and processes subcommands from command invocations.
 * </p>
 *
 * @since 0.7.0.0-RC1
 */
@Order
@Component
public final class SubcommandPreprocessor implements InvocationPreprocessor {

  /**
   * Processes the command invocation by handling subcommands.
   *
   * @param invocation The command invocation to preprocess.
   * @throws CommandException If an error occurs during the preprocessing.
   */
  @Override
  public void process(@NotNull CommandInvocation invocation) throws CommandException {
    Command subCommand = getSubCommand(invocation);
    if (subCommand != null) {
      invocation.setArguments(invocation.getArguments().stream().skip(1).toList());
      invocation.setCommand(subCommand);
      process(invocation);
    }
  }

  /**
   * Retrieves the subcommand from the command invocation.
   *
   * @param invocation The command invocation.
   * @return The subcommand, or {@code null} if not found.
   */
  private @Nullable Command getSubCommand(@NotNull CommandInvocation invocation) {
    if (invocation.getArguments().isEmpty()) {
      return null;
    }
    String possibleSubcommand = invocation.getArguments().get(0);
    return invocation.getCommand().getSubcommands()
        .stream()
        .sorted(Comparator.comparingInt(a -> a.getName().length()))
        .filter(subcommand -> possibleSubcommand.equalsIgnoreCase(subcommand.getName()))
        .findFirst()
        .orElse(null);
  }
}
