package dev.temez.springlify.starter.configuration;

import dev.temez.springlify.starter.plugin.SpringlifyPlugin;
import org.jetbrains.annotations.NotNull;
import org.springframework.context.ConfigurableApplicationContext;

public interface ConfigurationLoader {

  void load(
      @NotNull ConfigurableApplicationContext context,
      @NotNull SpringlifyPlugin plugin
  ) throws IllegalStateException;
}
