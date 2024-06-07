package dev.temez.springlify.platform.configuration.bukkit;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.FieldDefaults;

/**
 * Configuration class representing the color properties.
 *
 * <p>This class is used for configuring color properties using RGB values.</p>
 *
 * @since 0.7.0.0-RC1
 */
@Getter
@Setter
@ToString
@FieldDefaults(level = AccessLevel.PRIVATE)
public final class ColorConfiguration {

  /**
   * The red component of the color.
   */
  int red;

  /**
   * The green component of the color.
   */
  int green;

  /**
   * The blue component of the color.
   */
  int blue;
}
