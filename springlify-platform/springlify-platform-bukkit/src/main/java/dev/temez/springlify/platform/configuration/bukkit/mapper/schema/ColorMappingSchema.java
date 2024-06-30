package dev.temez.springlify.platform.configuration.bukkit.mapper.schema;

import dev.temez.springlify.platform.configuration.bukkit.ColorConfiguration;
import dev.temez.springlify.platform.configuration.mapper.schema.MappingSchema;
import org.bukkit.Color;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Component;

/**
 * Mapping schema for converting ColorConfiguration to Color.
 *
 * <p>This component is responsible for mapping properties from {@link ColorConfiguration} to {@link Color} instances.</p>
 *
 * @since 0.7.0.0-RC1
 */
@Component
public class ColorMappingSchema implements MappingSchema<ColorConfiguration, Color> {

  /**
   * Maps a ColorConfiguration instance to a Color instance.
   *
   * @param source The source ColorConfiguration to map from.
   * @return The resulting Color mapped from the configuration.
   */
  @Override
  public @NotNull Color map(@NotNull ColorConfiguration source) {
    return Color.fromBGR(source.getRed(), source.getGreen(), source.getBlue());
  }
}
