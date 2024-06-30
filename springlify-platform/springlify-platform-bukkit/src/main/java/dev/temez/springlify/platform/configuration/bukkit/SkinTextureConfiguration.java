package dev.temez.springlify.platform.configuration.bukkit;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.FieldDefaults;
import org.jetbrains.annotations.NotNull;

/**
 * Configuration class representing the skin texture properties.
 *
 * <p>This class is used for configuring skin texture properties, such as the texture and signature.</p>
 *
 * @since 0.7.0.0-RC1
 */
@Getter
@Setter
@ToString
@FieldDefaults(level = AccessLevel.PRIVATE)
public class SkinTextureConfiguration {

  /**
   * The texture of the skin.
   */
  @NotNull
  String texture;

  /**
   * The signature of the skin.
   */
  @NotNull
  String signature;
}
