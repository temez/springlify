package dev.temez.springlify.platform.bukkit.configuration.converter;

import java.util.Optional;
import org.bukkit.potion.PotionEffectType;
import org.jetbrains.annotations.Nullable;
import org.springframework.boot.context.properties.ConfigurationPropertiesBinding;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

/**
 * The {@code StringPotionEffectTypeConverter} class is a Spring converter that converts
 * a String representation of a potion effect type into a {@code PotionEffectType} object.
 * This converter is used for binding potion effect types from configuration properties.
 *
 * <p>Instances of this converter can be registered in the Spring context to enable the conversion
 * of potion effect types to {@code PotionEffectType} objects during property binding.</p>
 *
 * @since 0.5.9.8dev
 */
@Component
@ConfigurationPropertiesBinding
public final class StringPotionEffectTypeConverter implements Converter<String, PotionEffectType> {

  @Override
  public @Nullable PotionEffectType convert(@Nullable String source) {

    return Optional.ofNullable(source)
        .map(PotionEffectType::getByName)
        .orElse(null);
  }
}
