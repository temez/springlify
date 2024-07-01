package dev.temez.springlify.platform.configuration.bukkit.item.meta;

import dev.temez.springlify.platform.configuration.bukkit.ColorConfiguration;
import dev.temez.springlify.platform.configuration.bukkit.PotionEffectConfiguration;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.FieldDefaults;
import org.bukkit.potion.PotionType;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.springframework.boot.context.properties.NestedConfigurationProperty;

import java.util.ArrayList;
import java.util.List;

/**
 * Configuration class representing the item meta properties for potions.
 *
 * <p>This class is used for configuring the item meta properties specific to potions, such as effects and color.</p>
 *
 * @since 0.7.0.0-RC1
 */
@Getter
@Setter
@ToString
@FieldDefaults(level = AccessLevel.PRIVATE)
public class PotionItemMetaConfiguration {

  /**
   * The list of potion effects applied to the potion.
   */
  @NotNull
  List<PotionEffectConfiguration> effects = new ArrayList<>();

  /**
   * The main effect type of the potion.
   */
  @Nullable
  PotionType mainEffectType;

  /**
   * The color configuration for the potion.
   */
  @Nullable
  @NestedConfigurationProperty
  ColorConfiguration color;
}
