package dev.temez.springlify.platform.bukkit.configuration;

import dev.temez.springlify.platform.commons.configuration.Configurable;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.FieldDefaults;
import org.bukkit.Location;
import org.bukkit.World;
import org.jetbrains.annotations.NotNull;

/**
 * The {@code ConfigurableLocation} class represents a location in a Minecraft world that can be
 * configured with specific coordinates, yaw, and pitch. It implements the
 * {@code PlatformConfigurable} interface, allowing it to be used as a configurable component
 * with a platform-specific object.
 *
 * <p>Instances of this class can be created with the specified world, coordinates, yaw, and pitch,
 * and the configured location can be retrieved using the {@code getPlatformObject} method.</p>
 *
 * @since 0.5.9.7dev
 */
@Getter
@Setter
@ToString
@SuppressWarnings("checkstyle:MemberName")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ConfigurableLocation implements Configurable<Location> {

  /**
   * The world of the location.
   */
  @NotNull
  World world;

  /**
   * The x-coordinate of the location.
   */
  double x;

  /**
   * The y-coordinate of the location.
   */
  double y;

  /**
   * The z-coordinate of the location.
   */
  double z;

  /**
   * The yaw of the location.
   */
  float yaw;

  /**
   * The pitch of the location.
   */
  float pitch;

  /**
   * Converts this configurable location to a platform-specific location.
   *
   * @return The converted location.
   */
  @Override
  public @NotNull Location getPlatformObject(Object @NotNull ... params) {
    return new Location(world, x, y, z, yaw, pitch);
  }
}

