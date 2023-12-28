package dev.temez.springlify.platform.bukkit.configuration.converter;

import org.bukkit.NamespacedKey;
import org.bukkit.enchantments.Enchantment;
import org.jetbrains.annotations.Nullable;
import org.springframework.boot.context.properties.ConfigurationPropertiesBinding;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;


/**
 * The {@code StringEnchantmentConverter} class is a Spring converter that converts a String
 * representation of an enchantment key into an {@code Enchantment} object. This converter
 * is used for binding enchantment keys from configuration properties.
 *
 * <p>Instances of this converter can be registered in the Spring context to enable the conversion
 * of enchantment keys to {@code Enchantment} objects during property binding.</p>
 *
 * @since 0.5.9.8dev
 */
@Component
@ConfigurationPropertiesBinding
public final class StringEnchantmentConverter implements Converter<String, Enchantment> {

  @Override
  public @Nullable Enchantment convert(@Nullable String source) {
    if (source == null) {
      return null;
    }
    return Enchantment.getByKey(NamespacedKey.minecraft(source.toLowerCase()));
  }
}
