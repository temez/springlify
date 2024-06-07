package dev.temez.springlify.platform.configuration.bukkit.mapper.schema;

import dev.temez.springlify.platform.configuration.bukkit.PotionEffectConfiguration;
import dev.temez.springlify.platform.configuration.mapper.schema.MappingSchema;
import org.bukkit.potion.PotionEffect;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Component;

/**
 * Mapping schema for converting PotionEffectConfiguration to PotionEffect.
 *
 * <p>This component is responsible for mapping properties from {@link PotionEffectConfiguration} to {@link PotionEffect} instances.</p>
 *
 * @since 0.7.0.0-RC1
 */
@Component
public final class PotionEffectMappingSchema implements MappingSchema<PotionEffectConfiguration, PotionEffect> {

  /**
   * Maps a PotionEffectConfiguration instance to a PotionEffect instance.
   *
   * @param source The source PotionEffectConfiguration to map from.
   * @return The resulting PotionEffect mapped from the configuration.
   */
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
