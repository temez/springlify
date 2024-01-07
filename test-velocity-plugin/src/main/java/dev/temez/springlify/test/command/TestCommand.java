package dev.temez.springlify.test.command;

import com.velocitypowered.api.command.CommandSource;
import dev.temez.springlify.commander.commons.annotation.Command;
import dev.temez.springlify.commander.commons.annotation.RootCommand;
import dev.temez.springlify.commander.commons.annotation.Variadic;
import dev.temez.springlify.commander.commons.validaiton.annotation.RequirePermission;
import dev.temez.springlify.commander.velocity.validation.annotation.PermitServers;
import java.util.Arrays;
import org.jetbrains.annotations.NotNull;

@Command(
    name = "test",
    type = Command.CommandType.SHARED,
    description = "commands.test.description"
)
public class TestCommand {

  @RootCommand
  public void execute(@NotNull CommandSource player,
                      @NotNull String string,
                      @NotNull @Variadic String[] message
  ) {
    System.out.println(string + " " + Arrays.toString(message));
  }
}
