package dev.temez.springlify.commander.commons.help;

import dev.temez.springlify.commander.commons.command.RegisteredCommand;
import dev.temez.springlify.commander.commons.sender.Sender;
import org.jetbrains.annotations.NotNull;

public interface CommandHelpService {

  void sendHelpMessage(@NotNull Sender<?> sender, @NotNull RegisteredCommand command);

}
