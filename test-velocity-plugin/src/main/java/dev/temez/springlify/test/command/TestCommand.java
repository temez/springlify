package dev.temez.springlify.test.command;

import com.velocitypowered.api.proxy.Player;
import dev.temez.springlify.commander.commons.annotation.Command;
import dev.temez.springlify.commander.commons.annotation.CommandRoot;
import dev.temez.springlify.commander.commons.validaiton.annotation.RequirePermission;
import dev.temez.springlify.commander.velocity.validation.annotation.PermitServers;
import org.jetbrains.annotations.NotNull;

@Command(
    name = "test",
    type = Command.CommandType.INGAME,
    description = "commands.test.description"
)
public class TestCommand {

  @CommandRoot
  @PermitServers("auth")
  @RequirePermission("test.command")
  public void execute(@NotNull Player player) {

  }
}
