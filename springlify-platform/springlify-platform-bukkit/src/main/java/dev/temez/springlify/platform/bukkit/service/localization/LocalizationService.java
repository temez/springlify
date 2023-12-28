package dev.temez.springlify.platform.bukkit.service.localization;

import net.kyori.adventure.text.Component;
import org.bukkit.command.CommandSender;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

public interface LocalizationService {

  @NotNull
  Component getLocalizedMessage(
      @NotNull CommandSender sender,
      @NotNull String key,
      @NotNull Object... replacers);

  @NotNull
  String getLocalizedMessageString(
      @NotNull CommandSender sender,
      @NotNull String key,
      @NotNull Object... replacers
  );

  @NotNull
  ItemStack getLocalizedItemStack(
      @NotNull CommandSender sender,
      @NotNull String key,
      @NotNull Object... replacers
  );
}
