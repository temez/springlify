package dev.temez.springlify.commander.velocity.chat;

import com.velocitypowered.api.command.CommandSource;
import dev.temez.springlify.commander.commons.chat.CommanderChatService;
import dev.temez.springlify.commander.commons.chat.CommanderLocalizationService;
import dev.temez.springlify.commander.commons.sender.Sender;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import net.kyori.adventure.text.Component;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;

/**
 * Implementation of the CommanderChatService interface for handling command-related chat messages.
 *
 * @since 0.5.8.9dev
 */
@Service
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public final class CommanderChatServiceImpl implements CommanderChatService {

  @NotNull
  CommanderLocalizationService localizationService;

  /**
   * {@inheritDoc}
   */
  @Override
  public void sendErrorMessage(@NotNull Sender<?> sender, @NotNull String key,
                               Object @NotNull ... replacers) {
    Component localizedMessage = localizationService.localizeComponent(sender, key, replacers);
    sendMessage(sender, localizedMessage);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void sendMessage(@NotNull Sender<?> sender, @NotNull Component component) {
    ((CommandSource) sender.getPlatformSender()).sendMessage(component);
  }
}
