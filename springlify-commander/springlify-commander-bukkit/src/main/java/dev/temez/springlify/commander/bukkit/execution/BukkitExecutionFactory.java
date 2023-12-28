package dev.temez.springlify.commander.bukkit.execution;

import dev.temez.springlify.commander.bukkit.sender.BukkitCommandSender;
import dev.temez.springlify.commander.commons.command.RegisteredCommand;
import dev.temez.springlify.commander.commons.execution.CommandExecution;
import dev.temez.springlify.commander.commons.execution.CommandExecutionFactory;
import java.util.List;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;

/**
 * Implementation of {@link CommandExecutionFactory} for the Bukkit platform.
 *
 * <p>This factory creates instances of {@link CommandExecution}
 * for the execution of registered commands.</p>
 *
 * @since 0.5.8.9dev
 */
public class BukkitExecutionFactory implements CommandExecutionFactory {

  /**
   * {@inheritDoc}
   *
   * @param command The {@link RegisteredCommand} to be executed.
   * @param objects Additional objects required for command execution.
   *                - objects[0]: The {@link CommandSender} representing the command executor.
   *                - objects[1]: The list of string arguments for the command.
   * @return The created {@link CommandExecution} instance.
   */
  @Override
  @SuppressWarnings("unchecked")
  public @NotNull CommandExecution create(
      @NotNull RegisteredCommand command,
      Object @NotNull ... objects
  ) {
    return CommandExecution.builder()
        .command(command)
        .commandSender(new BukkitCommandSender((CommandSender) objects[0]))
        .arguments((List<String>) objects[1])
        .build();
  }
}
