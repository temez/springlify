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
 *
 * @since 0.5.9.8dev
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
      Object @NotNull ... replacers
  ) {
    sender.sendMessage(localizationService.getLocalizedMessage(sender, message, replacers));
  }
}
