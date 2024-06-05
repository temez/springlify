package dev.temez.springlify.platform.configuration.bukkit.mapper.schema;

import dev.temez.springlify.platform.configuration.bukkit.ColorConfiguration;
import dev.temez.springlify.platform.configuration.mapper.schema.MappingSchema;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.bukkit.Color;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
class ColorMappingSchemaTest {

  MappingSchema<ColorConfiguration, Color> colorMappingSchema = new ColorMappingSchema();

  @Test
  void givenColorConfiguration_whenMap_thenShouldReturnBukkitColor() {
    ColorConfiguration colorConfiguration = new ColorConfiguration(255, 255, 255);

    Color color = colorMappingSchema.map(colorConfiguration);

    assertThat(color).isNotNull();
    assertThat(color.getRed()).isEqualTo(255);
    assertThat(color.getGreen()).isEqualTo(255);
    assertThat(color.getBlue()).isEqualTo(255);
  }

}