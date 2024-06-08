package dev.temez.springlify.commander.exception.handler;

import dev.temez.springlify.commander.command.invocation.CommandInvocation;
import dev.temez.springlify.commander.exception.CommanderException;
import org.jetbrains.annotations.NotNull;

public interface CommanderExceptionHandler {

  void handle(@NotNull CommandInvocation invocation, @NotNull CommanderException exception);
}
