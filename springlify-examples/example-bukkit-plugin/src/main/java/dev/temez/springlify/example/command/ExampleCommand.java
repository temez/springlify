package dev.temez.springlify.example.command;

import dev.temez.springlify.commander.annotation.CommanderCommand;
import dev.temez.springlify.commander.annotation.CommandRoot;
import dev.temez.springlify.commander.command.CommandType;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

@CommanderCommand(
    name = "example",
    description = "commands.example.description",
    type = CommandType.CONSOLE
)
public class ExampleCommand {


  @CommandRoot
  public void execute(@NotNull ConsoleCommandSender commandSender, @NotNull Integer someInteger) {

  }

  @CommanderCommand(
      name = "sub",
      description = "commands.example.sub.description"
  )
  public void executeSub(@NotNull Player player, @NotNull Integer someInteger) {

  }

  @CommanderCommand(
      name = "othersub",
      description = "commands.example.othersub.description"
  )
  public static class SubExampleCommand {

    @CommandRoot
    public void execute(@NotNull CommandSender commandSender) {

    }

    @CommanderCommand(
        name = "sub",
        description = "commands.example.othersub.sub.description"
    )
    public void executeSub(@NotNull CommandSender commandSender, @NotNull Integer someInteger) {

    }
  }
}
