package dev.temez.springlify.platform.bukkit.configuration.localization;

import dev.temez.springlify.platform.bukkit.configuration.item.ConfigurableItem;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public interface LocalizationConfiguration {

  @Nullable String getMessage(@NotNull String key);

  @Nullable ConfigurableItem getItem(@NotNull String key);

}
