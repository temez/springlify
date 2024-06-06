package dev.temez.springlify.example.command;

import dev.temez.springlify.commander.annotation.CommandRoot;
import dev.temez.springlify.commander.annotation.CommanderCommand;
import dev.temez.springlify.commander.command.CommandType;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

@CommanderCommand(
    name = "example",
    description = "commands.example.description",
    type = CommandType.SHARED
)
public class ExampleCommand {


  @CommandRoot
  public void execute(@NotNull CommandSender commandSender, @NotNull Integer someInteger) {
    System.out.println(commandSender);
    System.out.println(someInteger);
  }

  @CommanderCommand(
      name = "sub",
      description = "commands.example.sub.description",
      type = CommandType.INGAME
  )
  public void executeSub(@NotNull Player player, @NotNull Integer someInteger) {

  }

  @CommanderCommand(
      name = "othersub",
      description = "commands.example.othersub.description",
      type = CommandType.SHARED
  )
  public static class SubExampleCommand {

    @CommanderCommand(
        name = "sub",
        description = "commands.example.othersub.sub.description",
        type = CommandType.SHARED
    )
    public void executeSub(@NotNull CommandSender commandSender, @NotNull Integer someInteger) {
      System.out.println(someInteger);
    }
  }
}
