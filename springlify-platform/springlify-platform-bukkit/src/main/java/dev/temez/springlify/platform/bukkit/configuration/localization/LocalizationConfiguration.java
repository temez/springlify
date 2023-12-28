package dev.temez.springlify.platform.bukkit.configuration.localization;

import dev.temez.springlify.platform.bukkit.configuration.item.ConfigurableItem;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * Defines the contract for localization configuration, providing methods to retrieve messages and
 * configurable items.
 *
 * @since 0.5.9.8dev
 */
public interface LocalizationConfiguration {

  /**
   * Retrieves the localized message for the given key.
   *
   * @param key The key associated with the desired message.
   * @return The localized message, or {@code null} if the message is not found.
   * @since 0.6.9.8dev
   */
  @Nullable String getMessage(@NotNull String key);

  /**
   * Retrieves the configurable item for the given key.
   *
   * @param key The key associated with the desired configurable item.
   * @return The configurable item, or {@code null} if the item is not found.
   * @since 0.6.9.8dev
   */
  @Nullable ConfigurableItem getItem(@NotNull String key);

}
