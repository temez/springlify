package dev.temez.springlify.commons.plugin;

import dev.temez.springlify.commons.initializer.SpringlifyInitializer;
import dev.temez.springlify.commons.initializer.SpringlifyInitializerImpl;
import dev.temez.springlify.commons.server.ServerPlatformAdapter;
import java.io.File;
import org.jetbrains.annotations.NotNull;

/**
 * The {@code SpringlifyPlugin} interface defines methods that must be implemented by classes
 * representing plugins that integrate with the Spring framework.
 */
public interface SpringlifyPlugin {

  /**
   * Returns plugin name.
   *
   * @return plugin name.
   */
  @NotNull String getName();

  /**
   * Gets the main application class for the plugin.
   *
   * @return The main application class.
   */
  @NotNull Class<?> getApplicationClass();

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
  default @NotNull SpringlifyInitializer getInitializer() {
    return new SpringlifyInitializerImpl();
  }
}
