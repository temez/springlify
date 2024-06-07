package dev.temez.springlify.platform.configuration.bukkit.converter;

import org.bukkit.NamespacedKey;
import org.bukkit.enchantments.Enchantment;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.springframework.boot.context.properties.ConfigurationPropertiesBinding;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

/**
 * Converter for converting a string representation of an enchantment to an Enchantment object.
 *
 * <p>This converter is used by Spring Boot's type conversion mechanism to convert string values to Enchantment objects
 * when binding configuration properties.</p>
 *
 * @see Enchantment
 * @since 0.7.0.0-RC1
 */
@Component
@ConfigurationPropertiesBinding
public final class StringEnchantmentConverter implements Converter<String, Enchantment> {

  /**
   * Converts the given string representation of an enchantment to an Enchantment object.
   *
   * <p>This method converts the string representation to an Enchantment object using the enchantment's key.</p>
   *
   * @param source the string representation of the enchantment
   * @return the corresponding Enchantment object, or {@code null} if the string does not represent a valid enchantment
   */
  @Override
  public @Nullable Enchantment convert(@NotNull String source) {
    return Enchantment.getByKey(NamespacedKey.minecraft(source.toLowerCase()));
  }
}
