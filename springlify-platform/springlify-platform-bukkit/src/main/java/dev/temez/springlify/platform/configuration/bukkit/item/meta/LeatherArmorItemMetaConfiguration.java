package dev.temez.springlify.platform.configuration.bukkit.item.meta;

import dev.temez.springlify.platform.configuration.bukkit.ColorConfiguration;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.FieldDefaults;
import org.jetbrains.annotations.NotNull;
import org.springframework.boot.context.properties.NestedConfigurationProperty;

/**
 * Configuration class representing the item meta properties for leather armor.
 *
 * <p>This class is used for configuring the item meta properties specific to leather armor, such as color.</p>
 *
 * @since 0.7.0.0-RC1
 */
@Getter
@Setter
@ToString
@FieldDefaults(level = AccessLevel.PRIVATE)
public final class LeatherArmorItemMetaConfiguration {

  /**
   * The color configuration for the leather armor.
   */
  @NotNull
  @NestedConfigurationProperty
  ColorConfiguration color;
}
