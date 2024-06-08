package dev.temez.springlify.platform.configuration.bukkit.mapper.schema;

import dev.temez.springlify.platform.configuration.bukkit.LocationConfiguration;
import dev.temez.springlify.platform.configuration.mapper.schema.MappingSchema;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Component;

/**
 * Mapping schema for converting LocationConfiguration to Location.
 *
 * <p>This component is responsible for mapping properties from {@link LocationConfiguration} to {@link Location} instances.</p>
 *
 * @since 0.7.0.0-RC1
 */
@Component
public final class LocationMappingSchema implements MappingSchema<LocationConfiguration, Location> {

  /**
   * Maps a LocationConfiguration instance to a Location instance.
   *
   * @param source The source LocationConfiguration to map from.
   * @return The resulting Location mapped from the configuration.
   */
  @Override
  public @NotNull Location map(@NotNull LocationConfiguration source) {
    return new Location(
        Bukkit.getWorld(source.getWorld()),
        source.getX(),
        source.getY(),
        source.getZ(),
        source.getYaw(),
        source.getPitch()
    );
  }
}
