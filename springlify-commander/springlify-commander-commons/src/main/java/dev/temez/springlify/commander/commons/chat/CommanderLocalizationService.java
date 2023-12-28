package dev.temez.springlify.commander.commons.chat;

import dev.temez.springlify.commander.commons.sender.Sender;
import net.kyori.adventure.text.Component;
import org.jetbrains.annotations.NotNull;

/**
 * Interface for a service handling localization of messages for a Commander plugin.
 *
 * @since 0.5.8.9dev
 */
public interface CommanderLocalizationService {

  /**
   * Localizes a message identified by the given key, replacing placeholders with provided values,
   * and returns the result as a Component.
   *
   * @param sender     The sender for whom the message is localized.
   * @param messageKey The key identifying the message to be localized.
   * @param replacers  The values to replace placeholders in the message.
   * @return The localized message as a Component.
   */
  @NotNull Component localizeComponent(
      @NotNull Sender<?> sender,
      @NotNull String messageKey,
      Object @NotNull ... replacers
  );

  /**
   * Localizes a message identified by the given key, replacing placeholders with provided values,
   * and returns the result as a String.
   *
   * @param sender     The sender for whom the message is localized.
   * @param messageKey The key identifying the message to be localized.
   * @param replacers  The values to replace placeholders in the message.
   * @return The localized message as a String.
   */
  @NotNull String localizeString(
      @NotNull Sender<?> sender,
      @NotNull String messageKey,
      Object @NotNull ... replacers
  );
}
