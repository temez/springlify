package dev.temez.springlify.starter.plugin;

import dev.temez.springlify.starter.initializer.SpringlifyInitializer;
import dev.temez.springlify.starter.initializer.event.ContextPreShutdownEvent;
import dev.temez.springlify.starter.server.ServerPlatformAdapter;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.springframework.context.ConfigurableApplicationContext;

import java.io.File;

public interface SpringlifyPlugin {

  default void initialize() {
    setContext(getInitializer().initialize(this));
  }

  ;

  default void shutdown() {
    if (getContext() == null) {
      throw new IllegalStateException("Springlify plugin wos not been properly initialized.");
    }
    getContext().publishEvent(new ContextPreShutdownEvent(this));
    getContext().close();
  }

  @Nullable
  ConfigurableApplicationContext getContext();

  void setContext(@NotNull ConfigurableApplicationContext applicationContext);

  @NotNull File getDataFolder();

  @NotNull ServerPlatformAdapter getServerPlatformAdapter();

  @NotNull SpringlifyInitializer getInitializer();
}
