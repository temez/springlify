package dev.temez.springlify.platform.configuration.bukkit.converter;

import org.bukkit.potion.PotionEffectType;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.springframework.boot.context.properties.ConfigurationPropertiesBinding;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

/**
 * Converter for converting a string representation of a potion effect type to a PotionEffectType object.
 *
 * <p>This converter is used by Spring Boot's type conversion mechanism to convert string values to PotionEffectType
 * objects when binding configuration properties.</p>
 *
 * @see PotionEffectType
 * @since 0.7.0.0-RC1
 */
@Component
@ConfigurationPropertiesBinding
public class StringPotionEffectTypeConverter implements Converter<String, PotionEffectType> {

  /**
   * Converts the given string representation of a potion effect type to a PotionEffectType object.
   *
   * <p>This method converts the string representation to a PotionEffectType object using the effect type's name.</p>
   *
   * @param source the string representation of the potion effect type
   * @return the corresponding PotionEffectType object, or {@code null} if the string does not represent a valid potion effect type
   */
  @Override
  public @Nullable PotionEffectType convert(@NotNull String source) {
    return PotionEffectType.getByName(source);
  }
}
