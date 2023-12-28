package dev.temez.springlify.commander.commons.chat;

import dev.temez.springlify.commander.commons.sender.Sender;
import net.kyori.adventure.text.Component;
import org.jetbrains.annotations.NotNull;

public interface CommanderLocalizationService {

  @NotNull Component localizeComponent(
      @NotNull Sender<?> sender,
      @NotNull String messageKey,
      Object @NotNull ... replacers
  );

  @NotNull String localizeString(
      @NotNull Sender<?> sender,
      @NotNull String messageKey,
      Object @NotNull ... replacers
  );
}
