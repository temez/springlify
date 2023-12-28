package dev.temez.springlify.starter.commons.configuraiton;

import dev.temez.springlify.starter.commons.exception.SpringlifyException;
import dev.temez.springlify.starter.commons.plugin.SpringlifyPlugin;
import org.jetbrains.annotations.NotNull;
import org.springframework.context.ConfigurableApplicationContext;

/**
 * The {@code ConfigurationLoader} interface defines operations for loading configuration
 * into a Spring application context within the context of a Springlify plugin.
 *
 * <p>Implementations of this interface are responsible for loading configuration settings
 * and integrating them into the provided Spring application context. They are typically
 * used during the initialization phase of a Springlify plugin.</p>
 *
 * @since 0.5.9.3dev
 */
public interface ConfigurationLoader {

  /**
   * Loads configuration into the specified Spring application context within the context
   * of the given Springlify plugin.
   *
   * @param context The Spring application context to which the configuration should be loaded.
   * @param plugin  The Springlify plugin associated with the configuration loading process.
   * @throws SpringlifyException If an error occurs during the configuration loading process.
   */
  void load(
      @NotNull ConfigurableApplicationContext context,
      @NotNull SpringlifyPlugin plugin
  ) throws SpringlifyException;
}
