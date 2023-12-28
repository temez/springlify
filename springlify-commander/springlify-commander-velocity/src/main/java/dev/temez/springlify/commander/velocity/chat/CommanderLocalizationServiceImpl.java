package dev.temez.springlify.commander.velocity.chat;

import com.velocitypowered.api.command.CommandSource;
import dev.temez.springlify.commander.commons.chat.CommanderLocalizationService;
import dev.temez.springlify.commander.commons.sender.Sender;
import dev.temez.springlify.platform.velocity.service.localization.LocalizationService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import net.kyori.adventure.text.Component;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;

/**
 * Implementation of {@link CommanderLocalizationService} that uses a {@link LocalizationService}.
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
   *
   * <p>Localizes a message to a {@link Component} for the specified sender with optional
   * replacers.</p>
   *
   * @param sender     The command sender.
   * @param messageKey The key of the message to localize.
   * @param replacers  Optional replacers to replace placeholders in the message.
   * @return The localized message as a {@link Component}.
   */
  @Override
  public @NotNull Component localizeComponent(
      @NotNull Sender<?> sender,
      @NotNull String messageKey,
      Object @NotNull ... replacers
  ) {
    return localizationService.getLocalizedMessage(
        (CommandSource) sender.getPlatformSender(),
        messageKey,
        replacers
    );
  }

  /**
   * {@inheritDoc}
   *
   * <p>Localizes a message to a string for the specified sender with optional replacers.</p>
   *
   * @param sender     The command sender.
   * @param messageKey The key of the message to localize.
   * @param replacers  Optional replacers to replace placeholders in the message.
   * @return The localized message as a string.
   */
  @Override
  public @NotNull String localizeString(
      @NotNull Sender<?> sender,
      @NotNull String messageKey,
      Object @NotNull ... replacers
  ) {
    return localizationService.getLocalizedMessageString(
        (CommandSource) sender.getPlatformSender(),
        messageKey,
        replacers
    );
  }
}