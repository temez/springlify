package dev.temez.springlify.commander.commons.execution;

import dev.temez.springlify.commander.commons.command.RegisteredCommand;
import org.jetbrains.annotations.NotNull;

public interface CommandExecutionFactory {

  @NotNull CommandExecution createExecution(
      @NotNull RegisteredCommand registeredCommand,
      Object @NotNull ... objects
  );

}
