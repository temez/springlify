package dev.temez.springlify.platform.configuration.bukkit.mapper.schema;

import dev.temez.springlify.platform.configuration.bukkit.LocationConfiguration;
import dev.temez.springlify.platform.configuration.mapper.schema.MappingSchema;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Component;

@Component
public final class LocationMappingSchema implements MappingSchema<LocationConfiguration, Location> {

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
