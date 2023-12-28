package dev.temez.springlify.commander.commons.chat;

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
    sender.sendMessage(localizedMessage);
  }
}
