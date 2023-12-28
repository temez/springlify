package dev.temez.springlify.platform.velocity.service.localization;

import com.velocitypowered.api.command.CommandSource;
import net.kyori.adventure.text.Component;
import org.jetbrains.annotations.NotNull;

/**
 * The {@code LocalizationService} interface defines methods for retrieving localized messages
 * and formatting them as {@link Component} or {@link String}.
 *
 * @since 0.5.9.8dev
 */
public interface LocalizationService {

  /**
   * Gets the localized message as a {@link Component} for the specified key and formats it
   * with the provided replacers.
   *
   * @param sender    The command source to determine the locale
   *                  or other context-specific information.
   * @param key       The key for the localized message.
   * @param replacers The replacers used for formatting the message.
   * @return The formatted localized message as a {@link Component}.
   */
  @NotNull Component getLocalizedMessage(
      @NotNull CommandSource sender,
      @NotNull String key,
      @NotNull Object... replacers);

  /**
   * Gets the localized message as a plain {@link String} for the specified key and formats it
   * with the provided replacers.
   *
   * @param sender    The command source to determine the locale
   *                  or other context-specific information.
   * @param key       The key for the localized message.
   * @param replacers The replacers used for formatting the message.
   * @return The formatted localized message as a plain {@link String}.
   */
  @NotNull String getLocalizedMessageString(
      @NotNull CommandSource sender,
      @NotNull String key,
      @NotNull Object... replacers
  );
}
