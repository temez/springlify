package dev.temez.springlify.platform.bukkit.configuration.converter;

import java.util.Optional;
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.jetbrains.annotations.Nullable;
import org.springframework.boot.context.properties.ConfigurationPropertiesBinding;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

/**
 * The {@code StringWorldConverter} class is a Spring converter that converts a String
 * representation of a Minecraft world name into a {@code World} object. This converter
 * is used for binding world names from configuration properties.
 *
 * <p>Instances of this converter can be registered in the Spring context to enable
 * the conversion of world names to {@code World} objects during property binding.</p>
 *
 * @since 0.5.9.7dev
 */
@Component
@ConfigurationPropertiesBinding
public final class StringWorldConverter implements Converter<String, World> {

  @Override
  public @Nullable World convert(@Nullable String source) {
    return Optional.ofNullable(source)
        .map(Bukkit::getWorld)
        .orElse(null);
  }
}
