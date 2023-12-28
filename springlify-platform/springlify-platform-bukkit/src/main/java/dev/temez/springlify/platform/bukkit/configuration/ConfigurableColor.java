package dev.temez.springlify.platform.bukkit.configuration;

import dev.temez.springlify.platform.commons.configuration.Configurable;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;
import lombok.experimental.FieldDefaults;
import org.bukkit.Color;
import org.jetbrains.annotations.NotNull;

/**
 * The {@code ConfigurableColor} class represents a color in the RGB color model that can be
 * configured with specific red, green, and blue components. It implements the
 * {@code PlatformConfigurable} interface, allowing it to be used as a configurable component
 * with a platform-specific object.
 *
 * <p>Instances of this class can be created with the specified red, green, and blue components,
 * and the configured color can be retrieved using the {@code getPlatformObject} method.</p>
 *
 * @since 0.5.9.8dev
 */
@Getter
@ToString
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class ConfigurableColor implements Configurable<Color> {

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

  /**
   * Converts this configurable color to a platform-specific color.
   *
   * @return The converted color.
   */
  @Override
  public @NotNull Color getPlatformObject(Object @NotNull ... params) {
    return Color.fromRGB(red, green, blue);
  }
}
