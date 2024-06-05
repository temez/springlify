package dev.temez.springlify.platform.configuration.bukkit.mapper.schema;

import dev.temez.springlify.platform.configuration.bukkit.PotionEffectConfiguration;
import dev.temez.springlify.platform.configuration.mapper.schema.MappingSchema;
import org.bukkit.potion.PotionEffect;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Component;

@Component
public final class PotionEffectMappingSchema implements MappingSchema<PotionEffectConfiguration, PotionEffect> {

  @Override
  public @NotNull PotionEffect map(@NotNull PotionEffectConfiguration source) {
    return new PotionEffect(
        source.getType(),
        (int) (source.getDuration().toSeconds() * 20),
        source.getAmplifier(),
        source.isAmbient(),
        source.isShowIcon()
    );
  }
}
