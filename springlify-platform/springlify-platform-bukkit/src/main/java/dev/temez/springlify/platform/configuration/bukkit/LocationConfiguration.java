package dev.temez.springlify.platform.configuration.bukkit;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.FieldDefaults;
import org.jetbrains.annotations.NotNull;

/**
 * Configuration class representing the location properties.
 *
 * <p>This class is used for configuring location properties, such as world, coordinates, yaw, and pitch.</p>
 *
 * @since 0.7.0.0-RC1
 */
@Getter
@Setter
@ToString
@FieldDefaults(level = AccessLevel.PRIVATE)
public final class LocationConfiguration {

  /**
   * The world name.
   */
  @NotNull
  String world;

  /**
   * The x-coordinate.
   */
  double x;

  /**
   * The y-coordinate.
   */
  double y;

  /**
   * The z-coordinate.
   */
  double z;

  /**
   * The yaw angle.
   */
  float yaw;

  /**
   * The pitch angle.
   */
  float pitch;
}
