package dev.temez.springlify.platform.velocity.configuration.localization;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public interface LocalizationConfiguration {

  @Nullable String getMessage(@NotNull String key);

}
