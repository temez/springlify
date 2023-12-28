package dev.temez.springlify.platform.bukkit.service.localization;

import dev.temez.springlify.platform.bukkit.configuration.localization.LocalizationConfiguration;
import dev.temez.springlify.platform.bukkit.utility.ItemBuilder;
import dev.temez.springlify.platform.commons.text.converter.TextConverter;
import dev.temez.springlify.platform.commons.utility.TextUtility;
import java.util.Optional;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import net.kyori.adventure.text.Component;
import org.bukkit.Material;
import org.bukkit.command.CommandSender;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;

/**
 * Implementation of the {@link LocalizationService} interface that retrieves localized messages,
 * strings, and ItemStacks using a {@link LocalizationConfiguration} and {@link TextConverter}.
 *
 * @since 0.5.9.8dev
 */
@Service
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public final class LocalizationServiceImpl implements LocalizationService {

  @NotNull TextConverter textConverter;

  @NotNull LocalizationConfiguration localizationConfiguration;

  @Override
  public @NotNull Component getLocalizedMessage(
      @NotNull CommandSender sender,
      @NotNull String key,
      @NotNull Object... replacers
  ) {
    return textConverter.parse(getLocalizedMessageString(sender, key, replacers));
  }

  @Override
  public @NotNull String getLocalizedMessageString(
      @NotNull CommandSender sender,
      @NotNull String key,
      @NotNull Object... replacers
  ) {
    String localizedMessage = Optional
        .ofNullable(localizationConfiguration.getMessage(key))
        .orElse("messages." + key);
    return TextUtility.applyReplacers(localizedMessage, replacers);
  }

  @Override
  public @NotNull ItemStack getLocalizedItemStack(
      @NotNull CommandSender sender,
      @NotNull String key,
      @NotNull Object... replacers
  ) {
    return Optional.ofNullable(localizationConfiguration.getItem(key))
        .map(item -> item.getPlatformObject(replacers))
        .orElse(
            ItemBuilder
                .fromMaterial(Material.STONE)
                .setName("items." + key)
                .build()
        );
  }
}
