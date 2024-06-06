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

@Order
@Component
public final class SubcommandPreprocessor implements InvocationPreprocessor {

  @Override
  public void process(@NotNull CommandInvocation invocation) throws CommandException {
    Command subCommand = getSubCommand(invocation);
    if (subCommand != null) {
      invocation.setArguments(invocation.getArguments().stream().skip(1).toList());
      invocation.setCommand(subCommand);
      process(invocation);
    }
  }

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
