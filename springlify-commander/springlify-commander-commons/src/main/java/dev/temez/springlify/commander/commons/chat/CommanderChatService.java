package dev.temez.springlify.commander.commons.chat;

import dev.temez.springlify.commander.commons.sender.Sender;
import org.jetbrains.annotations.NotNull;

public interface CommanderChatService {

  void sendErrorMessage(
      @NotNull Sender<?> sender,
      @NotNull String key,
      Object @NotNull ... replacers
  );
}
