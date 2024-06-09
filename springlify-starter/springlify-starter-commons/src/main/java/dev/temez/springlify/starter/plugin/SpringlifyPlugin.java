package dev.temez.springlify.starter.plugin;

import dev.temez.springlify.starter.initializer.SpringlifyInitializer;
import dev.temez.springlify.starter.initializer.event.ContextPreShutdownEvent;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.springframework.context.ConfigurableApplicationContext;

import java.io.File;

/**
 * Interface representing a Springlify plugin.
 *
 * <p>This interface defines the contract for Bukkit plugins that integrate with the Springlify framework.
 * It includes methods for initializing and shutting down the Spring context, as well as accessing necessary
 * components such as the data folder, server platform adapter, and initializer.</p>
 *
 * @see ConfigurableApplicationContext
 * @see SpringlifyInitializer
 * @since 0.7.0.0-RC1
 */
public interface SpringlifyPlugin {

  /**
   * Initializes the Spring context for the plugin.
   * <p>By default, this method sets the application context using the {@link SpringlifyInitializer}.</p>
   */
  default void initialize() {
    setContext(getInitializer().initialize(this));
  }

  /**
   * Shuts down the Spring context for the plugin.
   * <p>This method publishes a {@link ContextPreShutdownEvent} before closing the context.</p>
   *
   * @throws IllegalStateException if the plugin has not been properly initialized
   */
  default void shutdown() {
    if (getContext() == null) {
      throw new IllegalStateException("Springlify plugin has not been properly initialized.");
    }
    getContext().publishEvent(new ContextPreShutdownEvent(this));
    getContext().close();
  }

  /**
   * Returns the current Spring application context.
   *
   * @return the current application context, or null if not initialized
   */
  @Nullable
  ConfigurableApplicationContext getContext();

  /**
   * Sets the current Spring application context.
   *
   * @param applicationContext the new application context
   */
  void setContext(@NotNull ConfigurableApplicationContext applicationContext);

  /**
   * Returns the data folder for the plugin.
   *
   * @return the data folder
   */
  @NotNull
  File getDataFolder();

  /**
   * Returns the Springlify initializer for the plugin.
   *
   * @return the initializer
   */
  @NotNull
  SpringlifyInitializer getInitializer();
}
