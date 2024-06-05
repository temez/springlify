package dev.temez.springlify.commander.command.preprocessor;

import dev.temez.springlify.commander.command.Command;
import dev.temez.springlify.commander.command.invocation.CommandInvocation;
import dev.temez.springlify.commander.command.invocation.CommandInvocationImpl;
import dev.temez.springlify.commander.exception.CommandException;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.springframework.stereotype.Component;

import java.util.Comparator;

@Component
public final class SubcommandPreprocessor implements ExecutionPreprocessor {

  @Override
  public void process(@NotNull CommandInvocation execution) throws CommandException {
    CommandInvocationImpl commandExecution = ((CommandInvocationImpl) execution);
    Command subCommand = getSubCommand(execution);
    if (subCommand != null) {
      commandExecution.setArguments(commandExecution.getArguments().stream().skip(1).toList());
      commandExecution.setCommand(subCommand);
      process(execution);
    }
  }

  private @Nullable Command getSubCommand(@NotNull CommandInvocation execution) {
    if (execution.getArguments().isEmpty()) {
      return null;
    }
    String possibleSubcommand = execution.getArguments().get(0);
    return execution.getCommand().getSubcommands()
        .stream()
        .sorted(Comparator.comparingInt(a -> a.getName().length()))
        .filter(subcommand -> possibleSubcommand.equalsIgnoreCase(subcommand.getName()))
        .findFirst()
        .orElse(null);
  }
}
