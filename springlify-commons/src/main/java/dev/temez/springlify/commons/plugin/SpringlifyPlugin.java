package dev.temez.springlify.commons.plugin;

import dev.temez.springlify.commons.initializer.SpringlifyInitializer;
import dev.temez.springlify.commons.server.ServerPlatformAdapter;
import java.io.File;
import java.util.Collections;
import java.util.List;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.config.BeanPostProcessor;

/**
 * The {@code SpringlifyPlugin} interface defines methods that must be implemented by classes
 * representing plugins that integrate with the Spring framework.
 */
public interface SpringlifyPlugin {

  /**
   * Gets the main application class for the plugin.
   *
   * @return The main application class.
   */
  @NotNull Class<?> getApplicationClass();

  /**
   * Gets the list of bean post processors to be registered with the Spring application context.
   *
   * @return A list of bean post processor classes.
   */
  default @NotNull List<Class<? extends BeanPostProcessor>> getPostProcessors() {
    return Collections.emptyList();
  }

  /**
   * Initializes the plugin. This method should be called during plugin startup.
   */
  void initialize();

  /**
   * Shuts down the plugin. This method should be called during plugin shutdown.
   */
  void shutdown();

  /**
   * Gets the data folder for the plugin.
   *
   * @return The data folder for the plugin.
   */
  @NotNull File getDataFolder();

  /**
   * Gets the server platform implementation.
   *
   * @return The server platform implementation.
   */
  @NotNull ServerPlatformAdapter getServerPlatformAdapter();

  /**
   * Get the spring application initializer.
   *
   * @return The spring application initializer.
   */
  @NotNull SpringlifyInitializer getInitializer();
}
