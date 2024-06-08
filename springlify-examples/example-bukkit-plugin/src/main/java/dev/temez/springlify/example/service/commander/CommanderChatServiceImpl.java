package dev.temez.springlify.example.service.commander;

import dev.temez.springlify.commander.command.sender.Sender;
import dev.temez.springlify.commander.service.chat.CommanderChatService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class CommanderChatServiceImpl implements CommanderChatService {

  @Override
  public void sendMessage(@NotNull Sender<?> sender, @NotNull String message, Object @NotNull ... replacers) {
    ((CommandSender) sender.getPlatformSender()).sendMessage(message);

  }

  @Override
  public void sendErrorMessage(@NotNull Sender<?> sender, @NotNull String message, Object @NotNull ... replacers) {
    sendMessage(sender, message, replacers);
  }
}
