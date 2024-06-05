package dev.temez.springlify.starter.initializer;

import dev.temez.springlify.starter.plugin.SpringlifyPlugin;
import org.jetbrains.annotations.NotNull;
import org.springframework.context.ConfigurableApplicationContext;

public interface SpringlifyInitializer {


  @NotNull ConfigurableApplicationContext initialize(@NotNull SpringlifyPlugin plugin);

}
