package dev.temez.springlify.example.command;

import dev.temez.springlify.commander.annotation.CommandRoot;
import dev.temez.springlify.commander.annotation.CommanderCommand;
import dev.temez.springlify.commander.command.CommandType;
import lombok.extern.slf4j.Slf4j;
import org.bukkit.World;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;

@CommanderCommand(
    name = "example",
    type = CommandType.SHARED
)
@Slf4j
public class ExampleCommand {

  @CommandRoot
  public void execute(@NotNull CommandSender commandSender, @NotNull World world) {
    log.info("This is example command!");
  }

  @CommanderCommand(
      name = "sub",
      type = CommandType.SHARED
  )
  public void executeSub(@NotNull CommandSender player, @NotNull Integer someInteger) {
    log.info("This is simple subcommand!");
  }

  @CommanderCommand(
      name = "othersub",
      type = CommandType.SHARED
  )
  public static class SubExampleCommand {

    @CommandRoot
    public void execute(@NotNull CommandSender commandSender, @NotNull World world) {
      log.info("This is othersub subcommand!");
    }

    @CommanderCommand(
        name = "sub",
        type = CommandType.SHARED
    )
    public void executeSub(@NotNull CommandSender commandSender, @NotNull Integer someInteger) {
      log.info("This is subcommand for othersub subcommand!");
    }
  }
}
