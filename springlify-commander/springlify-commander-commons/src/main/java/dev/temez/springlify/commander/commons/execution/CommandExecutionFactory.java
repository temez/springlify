package dev.temez.springlify.commander.commons.execution;

import dev.temez.springlify.commander.commons.command.RegisteredCommand;
import org.jetbrains.annotations.NotNull;

/**
 * Factory interface for creating CommandExecution instances.
 *
 * @since 0.5.8.9dev
 */
public interface CommandExecutionFactory {

  /**
   * Creates a CommandExecution instance for the specified registered command and objects.
   *
   * @param registeredCommand The registered command for which to create the execution.
   * @param objects           Objects to be used in the execution.
   * @return The created CommandExecution instance.
   */
  @NotNull
  CommandExecution create(
      @NotNull RegisteredCommand registeredCommand,
      Object @NotNull ... objects
  );
}
