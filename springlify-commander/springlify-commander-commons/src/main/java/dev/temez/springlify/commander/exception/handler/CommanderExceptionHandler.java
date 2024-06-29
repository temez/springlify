package dev.temez.springlify.commander.exception.handler;

import dev.temez.springlify.commander.command.invocation.CommandInvocation;
import org.jetbrains.annotations.NotNull;

public interface CommanderExceptionHandler {

  void handle(@NotNull CommandInvocation invocation, @NotNull Exception exception) throws RuntimeException;
}
