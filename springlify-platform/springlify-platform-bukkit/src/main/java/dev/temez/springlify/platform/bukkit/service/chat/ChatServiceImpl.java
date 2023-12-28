package dev.temez.springlify.platform.bukkit.service.chat;

import dev.temez.springlify.platform.bukkit.service.localization.LocalizationService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;


/**
 * Simple {@link ChatService} implementation.
 */
@Service
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public final class ChatServiceImpl implements ChatService {

  @NotNull LocalizationService localizationService;

  @Override
  public void sendMessage(
      @NotNull CommandSender sender,
      @NotNull String message,
      @NotNull Object... replacers
  ) {
    sender.sendMessage(localizationService.getLocalizedMessage(sender, message, replacers));
  }
}
