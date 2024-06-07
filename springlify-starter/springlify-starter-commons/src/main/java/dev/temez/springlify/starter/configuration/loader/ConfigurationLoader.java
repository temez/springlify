package dev.temez.springlify.starter.configuration.loader;

import dev.temez.springlify.starter.plugin.SpringlifyPlugin;
import org.jetbrains.annotations.NotNull;
import org.springframework.context.ConfigurableApplicationContext;

/**
 * Interface for loading configurations into the Spring context of Springlify plugins.
 *
 * <p>This interface defines a method for loading configurations into a {@link ConfigurableApplicationContext}
 * for a given {@link SpringlifyPlugin}. Implementations of this interface will provide the logic
 * for loading configurations from various sources into the Spring context.</p>
 *
 * @see ConfigurableApplicationContext
 * @see SpringlifyPlugin
 * @since 0.7.0.0-RC1
 */
public interface ConfigurationLoader {

  /**
   * Loads configurations into the specified {@link ConfigurableApplicationContext} for the given {@link SpringlifyPlugin}.
   *
   * @param context the Spring application context
   * @param plugin  the Springlify plugin instance
   * @throws IllegalStateException if there is an error during the loading process
   */
  void load(@NotNull ConfigurableApplicationContext context, @NotNull SpringlifyPlugin plugin) throws IllegalStateException;
}
