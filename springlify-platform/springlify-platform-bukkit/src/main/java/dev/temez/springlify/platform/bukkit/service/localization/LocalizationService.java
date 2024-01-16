package dev.temez.springlify.platform.bukkit.service.localization;

import net.kyori.adventure.text.Component;
import org.bukkit.command.CommandSender;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

/**
 * Defines the contract for a localization service, providing methods to retrieve localized
 * components, strings, and ItemStacks with support for replacement parameters.
 *
 * @since 0.5.9.8dev
 */
public interface LocalizationService {

  /**
   * Retrieves a localized Component for the given key with optional replacement parameters.
   *
   * @param sender    The CommandSender to whom the message is being sent.
   * @param key       The key associated with the desired localized message.
   * @param replacers Optional parameters to replace placeholders in the message.
   * @return The localized Component.
   */
  @NotNull
  Component getLocalizedMessage(
      @NotNull CommandSender sender,
      @NotNull String key,
      @NotNull Object... replacers);

  /**
   * Retrieves a localized string for the given key with optional replacement parameters.
   *
   * @param sender    The CommandSender to whom the message is being sent.
   * @param key       The key associated with the desired localized message.
   * @param replacers Optional parameters to replace placeholders in the message.
   * @return The localized string.
   */
  @NotNull
  String getLocalizedMessageString(
      @NotNull CommandSender sender,
      @NotNull String key,
      @NotNull Object... replacers
  );

  /**
   * Retrieves a localized ItemStack for the given key with optional replacement parameters.
   *
   * @param sender    The CommandSender to whom the message is being sent.
   * @param key       The key associated with the desired localized item.
   * @param replacers Optional parameters to replace placeholders in the item.
   * @return The localized ItemStack.
   */
  @NotNull
  ItemStack getLocalizedItemStack(
      @NotNull CommandSender sender,
      @NotNull String key,
      @NotNull Object... replacers
  );

  /**
   * Retrieves a localized string for the given key with optional replacement parameters.
   *
   * @param key       The key associated with the desired localized message.
   * @param replacers Optional parameters to replace placeholders in the message.
   * @return The localized string.
   */
  @NotNull
  String getLocalizedMessageString(
          @NotNull String key,
          @NotNull Object... replacers
  );

  /**
   * Retrieves a localized ItemStack for the given key with optional replacement parameters.
   *
   * @param key       The key associated with the desired localized item.
   * @param replacers Optional parameters to replace placeholders in the item.
   * @return The localized ItemStack.
   */
  @NotNull
  ItemStack getLocalizedItemStack(
          @NotNull String key,
          @NotNull Object... replacers
  );
}
