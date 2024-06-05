package dev.temez.springlify.platform.configuration.bukkit;

import lombok.*;
import lombok.experimental.FieldDefaults;
import org.bukkit.enchantments.Enchantment;
import org.jetbrains.annotations.NotNull;

@Setter
@Getter
@ToString
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public final class EnchantmentConfiguration {

  @NotNull
  Enchantment enchantment;

  int level = 1;

  boolean ignoreLevelRestriction = true;
}