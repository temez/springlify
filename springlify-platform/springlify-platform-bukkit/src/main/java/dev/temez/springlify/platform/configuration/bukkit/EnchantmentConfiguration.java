package dev.temez.springlify.platform.configuration.bukkit;

import lombok.*;
import lombok.experimental.FieldDefaults;
import org.bukkit.enchantments.Enchantment;
import org.jetbrains.annotations.NotNull;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.FieldDefaults;
import org.bukkit.enchantments.Enchantment;
import org.jetbrains.annotations.NotNull;

/**
 * Configuration class representing the enchantment properties.
 *
 * <p>This class is used for configuring enchantment properties, such as the enchantment itself, level, and level restriction.</p>
 *
 * @since 0.7.0.0-RC1
 */
@Getter
@Setter
@ToString
@FieldDefaults(level = AccessLevel.PRIVATE)
public final class EnchantmentConfiguration {

  /** The enchantment. */
  @NotNull
  Enchantment enchantment;

  /** The level of the enchantment. Default value is 1. */
  int level = 1;

  /** Indicates whether to ignore level restrictions. Default value is true. */
  boolean ignoreLevelRestriction = true;
}
