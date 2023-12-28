package dev.temez.springlify.platform.velocity.service.localization;

import com.velocitypowered.api.command.CommandSource;
import net.kyori.adventure.text.Component;
import org.jetbrains.annotations.NotNull;

public interface LocalizationService {

  @NotNull
  Component getLocalizedMessage(
      @NotNull CommandSource sender,
      @NotNull String key,
      @NotNull Object... replacers);

  @NotNull
  String getLocalizedMessageString(
      @NotNull CommandSource sender,
      @NotNull String key,
      @NotNull Object... replacers
  );
}
