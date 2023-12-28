package dev.temez.springlify.platform.velocity.service.localization;

import com.velocitypowered.api.command.CommandSource;
import dev.temez.springlify.platform.commons.text.converter.TextConverter;
import dev.temez.springlify.platform.commons.utility.TextUtility;
import dev.temez.springlify.platform.velocity.configuration.localization.LocalizationConfiguration;
import java.util.Optional;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import net.kyori.adventure.text.Component;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public final class LocalizationServiceImpl implements LocalizationService {

  @NotNull TextConverter textConverter;

  @NotNull LocalizationConfiguration localizationConfiguration;

  @Override
  public @NotNull Component getLocalizedMessage(
      @NotNull CommandSource sender,
      @NotNull String key,
      @NotNull Object... replacers
  ) {
    return textConverter.parse(getLocalizedMessageString(sender, key, replacers));
  }

  @Override
  public @NotNull String getLocalizedMessageString(
      @NotNull CommandSource sender,
      @NotNull String key,
      @NotNull Object... replacers
  ) {
    String localizedMessage = Optional
        .ofNullable(localizationConfiguration.getMessage(key))
        .orElse("messages." + key);
    return TextUtility.applyReplacers(localizedMessage, replacers);
  }
}
