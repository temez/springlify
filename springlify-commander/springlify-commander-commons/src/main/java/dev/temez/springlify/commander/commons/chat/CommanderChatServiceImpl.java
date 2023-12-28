package dev.temez.springlify.commander.commons.chat;

import dev.temez.springlify.commander.commons.sender.Sender;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public final class CommanderChatServiceImpl implements CommanderChatService {

  @NotNull CommanderLocalizationService localizationService;

  @Override
  public void sendErrorMessage(@NotNull Sender<?> sender, @NotNull String key,
                               Object @NotNull ... replacers) {
    sender.sendMessage(localizationService.localizeComponent(sender, key, replacers));
  }
}
