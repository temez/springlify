package dev.temez.springlify.platform.configuration.bukkit.mapper.schema;

import dev.temez.springlify.platform.configuration.bukkit.ColorConfiguration;
import dev.temez.springlify.platform.configuration.mapper.schema.MappingSchema;
import org.bukkit.Color;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Component;

@Component
public final class ColorMappingSchema implements MappingSchema<ColorConfiguration, Color> {

  @Override
  public @NotNull Color map(@NotNull ColorConfiguration source) {
    return Color.fromBGR(source.getRed(), source.getGreen(), source.getBlue());
  }
}
