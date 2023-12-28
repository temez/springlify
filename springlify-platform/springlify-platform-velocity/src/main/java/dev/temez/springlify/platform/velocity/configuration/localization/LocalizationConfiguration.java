package dev.temez.springlify.platform.velocity.configuration.localization;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * The {@code LocalizationConfiguration} interface defines methods for retrieving localized messages
 * based on a specified key.
 *
 * @since 0.5.9.8dev
 */
public interface LocalizationConfiguration {

  /**
   * Gets the localized message for the specified key.
   *
   * @param key The key for the localized message.
   * @return The localized message or {@code null} if the key is not found.
   */
  @Nullable String getMessage(@NotNull String key);
}
