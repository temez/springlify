package dev.temez.springlify.platform.configuration.bukkit.converter;

import org.bukkit.potion.PotionEffectType;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.springframework.boot.context.properties.ConfigurationPropertiesBinding;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
@ConfigurationPropertiesBinding
public final class StringPotionEffectTypeConverter implements Converter<String, PotionEffectType> {

  @Override
  public @Nullable PotionEffectType convert(@NotNull String source) {
    return PotionEffectType.getByName(source);
  }
}
