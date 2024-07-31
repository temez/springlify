package dev.temez.springlify.commander.exception.handler;

import dev.temez.springlify.commander.command.invocation.CommandInvocation;
import org.jetbrains.annotations.NotNull;

public interface CommandExceptionHandler {

  void handle(@NotNull CommandInvocation invocation, @NotNull Exception exception);

  boolean supports(@NotNull Exception exception);
}
