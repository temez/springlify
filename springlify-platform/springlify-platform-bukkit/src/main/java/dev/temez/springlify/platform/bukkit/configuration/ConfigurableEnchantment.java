package dev.temez.springlify.platform.bukkit.configuration;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;
import lombok.experimental.FieldDefaults;
import org.bukkit.enchantments.Enchantment;
import org.jetbrains.annotations.NotNull;

/**
 * The {@code ConfigurableEnchantment} class represents a configurable enchantment with properties
 * such as the enchantment type and level.
 *
 * <p>Instances of this class can be created with the specified enchantment type and level.</p>
 *
 * @since 0.5.9.8dev
 */
@Getter
@ToString
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class ConfigurableEnchantment {

  /**
   * The enchantment type.
   */
  @NotNull
  Enchantment enchantment;

  /**
   * The level of the enchantment.
   */
  int level;
}
