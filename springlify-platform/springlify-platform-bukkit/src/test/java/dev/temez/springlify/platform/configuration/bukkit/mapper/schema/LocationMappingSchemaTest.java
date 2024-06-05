package dev.temez.springlify.platform.configuration.bukkit.mapper.schema;

import dev.temez.springlify.platform.configuration.bukkit.LocationConfiguration;
import dev.temez.springlify.platform.configuration.mapper.schema.MappingSchema;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
class LocationMappingSchemaTest {

  MappingSchema<LocationConfiguration, Location> locationMappingSchema = new LocationMappingSchema();

  @Test
  void givenLocationConfiguration_whenMap_thenReturnBukkitLocation() {
    World world = mock(World.class);
    when(world.getName()).thenReturn("world");

    try (MockedStatic<Bukkit> bukkit = mockStatic(Bukkit.class)) {
      bukkit.when(() -> Bukkit.getWorld("world")).thenReturn(world);

      LocationConfiguration locationConfiguration = new LocationConfiguration(
          "world",
          100,
          100,
          100,
          180,
          90
      );

      Location location = locationMappingSchema.map(locationConfiguration);

      assertThat(location).isNotNull();
      assertThat(location.getWorld().getName()).isEqualTo(world.getName());
      assertThat(location.getX()).isEqualTo(100);
      assertThat(location.getY()).isEqualTo(100);
      assertThat(location.getZ()).isEqualTo(100);
      assertThat(location.getYaw()).isEqualTo(180);
      assertThat(location.getPitch()).isEqualTo(90);
    }
  }
}