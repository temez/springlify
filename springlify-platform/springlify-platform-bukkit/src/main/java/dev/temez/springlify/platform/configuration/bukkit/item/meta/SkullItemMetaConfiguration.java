package dev.temez.springlify.platform.configuration.bukkit.item.meta;

import dev.temez.springlify.platform.configuration.bukkit.SkinTextureConfiguration;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.FieldDefaults;
import org.jetbrains.annotations.Nullable;
import org.springframework.boot.context.properties.NestedConfigurationProperty;

/**
 * Configuration class representing the item meta properties for skulls.
 *
 * <p>This class is used for configuring the item meta properties specific to skulls, such as owner and skin.</p>
 *
 * @since 0.7.0.0-RC1
 */
@Getter
@Setter
@ToString
@FieldDefaults(level = AccessLevel.PRIVATE)
public class SkullItemMetaConfiguration {

  /**
   * The owner of the skull.
   */
  @Nullable
  String owner;

  /**
   * The skin texture configuration for the skull.
   */
  @Nullable
  @NestedConfigurationProperty
  SkinTextureConfiguration skin;
}
