package dev.temez.springlify.commander.bukkit.execution;

import dev.temez.springlify.commander.bukkit.sender.BukkitCommandSender;
import dev.temez.springlify.commander.commons.command.RegisteredCommand;
import dev.temez.springlify.commander.commons.execution.CommandExecution;
import dev.temez.springlify.commander.commons.execution.CommandExecutionFactory;
import java.util.List;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;

public class BukkitExecutionFactory implements CommandExecutionFactory {

  @Override
  @SuppressWarnings("unchecked")
  public @NotNull CommandExecution createExecution(
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
