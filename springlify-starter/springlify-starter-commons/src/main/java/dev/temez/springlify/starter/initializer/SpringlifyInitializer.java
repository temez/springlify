package dev.temez.springlify.starter.initializer;

import dev.temez.springlify.starter.plugin.SpringlifyPlugin;
import org.jetbrains.annotations.NotNull;
import org.springframework.context.ConfigurableApplicationContext;

/**
 * Interface for initializing the Spring context of Springlify plugins.
 *
 * <p>This interface defines a method for initializing a {@link ConfigurableApplicationContext} for a given
 * {@link SpringlifyPlugin}. Implementations of this interface will provide the logic for setting up
 * the Spring context.</p>
 *
 * @see ConfigurableApplicationContext
 * @see SpringlifyPlugin
 * @since 0.7.0.0-RC1
 */
public interface SpringlifyInitializer {

  /**
   * Initializes the Spring context for the specified {@link SpringlifyPlugin}.
   *
   * @param plugin the Springlify plugin instance
   * @return the initialized Spring application context
   * @throws IllegalStateException if there is an error during initialization
   */
  @NotNull
  ConfigurableApplicationContext initialize(@NotNull SpringlifyPlugin plugin) throws IllegalStateException;

}
