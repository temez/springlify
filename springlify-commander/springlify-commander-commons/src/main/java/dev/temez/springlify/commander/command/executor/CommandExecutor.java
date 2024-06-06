package dev.temez.springlify.commander.command.executor;

import dev.temez.springlify.commander.command.invocation.CommandInvocation;
import dev.temez.springlify.commander.exception.CommandExecutionException;
import org.jetbrains.annotations.NotNull;

public interface CommandExecutor {

  void execute(@NotNull CommandInvocation commandInvocation) throws CommandExecutionException;

}
