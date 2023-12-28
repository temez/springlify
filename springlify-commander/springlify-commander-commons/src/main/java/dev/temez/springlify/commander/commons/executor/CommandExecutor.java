package dev.temez.springlify.commander.commons.executor;

import dev.temez.springlify.commander.commons.exception.ConformableException;
import dev.temez.springlify.commander.commons.execution.CommandExecution;
import org.jetbrains.annotations.NotNull;

public interface CommandExecutor {

  void execute(@NotNull CommandExecution commandExecution) throws ConformableException;

}
