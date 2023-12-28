package dev.temez.springlify.commander.commons.exception.handler;

import dev.temez.springlify.commander.commons.exception.ConformableException;
import dev.temez.springlify.commander.commons.execution.CommandExecution;
import org.jetbrains.annotations.NotNull;

public interface CommanderExceptionHandler {

  void handle(@NotNull CommandExecution execution, @NotNull ConformableException exception);
}
