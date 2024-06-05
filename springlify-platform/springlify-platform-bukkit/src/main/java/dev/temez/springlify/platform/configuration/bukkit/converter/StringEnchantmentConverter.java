package dev.temez.springlify.platform.configuration.bukkit.converter;

import org.bukkit.NamespacedKey;
import org.bukkit.enchantments.Enchantment;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.springframework.boot.context.properties.ConfigurationPropertiesBinding;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;


@Component
@ConfigurationPropertiesBinding
public final class StringEnchantmentConverter implements Converter<String, Enchantment> {

  @Override
  public @Nullable Enchantment convert(@NotNull String source) {
    return Enchantment.getByKey(NamespacedKey.minecraft(source.toLowerCase()));
  }
}
