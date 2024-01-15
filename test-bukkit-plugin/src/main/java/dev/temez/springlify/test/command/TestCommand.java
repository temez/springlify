package dev.temez.springlify.test.command;

import dev.temez.springlify.commander.commons.annotation.Command;
import dev.temez.springlify.commander.commons.annotation.RootCommand;
import dev.temez.springlify.commander.commons.annotation.SubCommand;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;

@Command(
    type = Command.CommandType.SHARED,
    name = "test",
    description = "commands.test.description"
)

public class TestCommand {

  @RootCommand
  public void executeRoot(@NotNull CommandSender sender) {

  }

  @SubCommand(
      name = "sub",
      description = "commands.test.sub.description"
  )
  public void executeSub(@NotNull CommandSender sender, @NotNull Integer integer) {

  }
}
