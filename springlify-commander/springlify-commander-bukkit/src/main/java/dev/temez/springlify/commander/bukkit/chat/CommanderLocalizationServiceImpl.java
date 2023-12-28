package dev.temez.springlify.commander.bukkit.chat;

import dev.temez.springlify.commander.commons.chat.CommanderLocalizationService;
import dev.temez.springlify.commander.commons.sender.Sender;
import dev.temez.springlify.platform.bukkit.service.localization.LocalizationService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import net.kyori.adventure.text.Component;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;

/**
 * Implementation of {@link CommanderLocalizationService} that delegates
 * to a {@link LocalizationService} for localization functionality.
 *
 * @since 0.5.8.9dev
 */
@Service
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public final class CommanderLocalizationServiceImpl implements CommanderLocalizationService {

  @NotNull LocalizationService localizationService;

  /**
   * {@inheritDoc}
   */
  @Override
  public @NotNull Component localizeComponent(
      @NotNull Sender<?> sender,
      @NotNull String messageKey,
      Object @NotNull ... replacers
  ) {
    return localizationService.getLocalizedMessage(
        (CommandSender) sender.getPlatformSender(),
        messageKey,
        replacers
    );
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public @NotNull String localizeString(
      @NotNull Sender<?> sender,
      @NotNull String messageKey,
      Object @NotNull ... replacers
  ) {
    return localizationService.getLocalizedMessageString(
        (CommandSender) sender.getPlatformSender(),
        messageKey,
        replacers
    );
  }
}

