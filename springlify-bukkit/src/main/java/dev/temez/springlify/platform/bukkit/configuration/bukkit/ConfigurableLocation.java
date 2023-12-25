package dev.temez.springlify.platform.bukkit.configuration.bukkit;

import dev.temez.springlify.commons.configuraiton.PlatformConfigurable;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
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
@ToString
@RequiredArgsConstructor
@SuppressWarnings("checkstyle:MemberName")
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class ConfigurableLocation implements PlatformConfigurable<Location> {

  @NotNull
  World world;

  double x;

  double y;

  double z;

  float yaw;

  float pitch;

  @Override
  public @NotNull Location getPlatformObject() {
    return new Location(world, x, y, z, yaw, pitch);
  }
}
