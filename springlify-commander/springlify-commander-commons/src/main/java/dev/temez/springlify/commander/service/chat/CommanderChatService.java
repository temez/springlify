package dev.temez.springlify.commander.service.chat;

import dev.temez.springlify.commander.command.sender.Sender;
import org.jetbrains.annotations.NotNull;

public interface CommanderChatService {

  void sendMessage(@NotNull Sender<?> sender, @NotNull String message, Object @NotNull ... replacers);

  void sendErrorMessage(@NotNull Sender<?> sender, @NotNull String message, Object @NotNull ... replacers);
}
