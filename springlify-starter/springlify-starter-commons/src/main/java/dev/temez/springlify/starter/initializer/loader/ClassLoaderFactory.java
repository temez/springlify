package dev.temez.springlify.starter.initializer.loader;

import dev.temez.springlify.starter.plugin.SpringlifyPlugin;
import org.jetbrains.annotations.NotNull;

/**
 * Interface for creating class loaders for Springlify plugins.
 *
 * <p>This interface defines a method for creating a {@link ClassLoader} specific to a {@link SpringlifyPlugin}.
 * Implementations of this interface will provide the logic for creating and configuring the class loader.</p>
 *
 * @see ClassLoader
 * @see SpringlifyPlugin
 * @since 0.7.0.0-RC1
 */
public interface ClassLoaderFactory {

  /**
   * Creates a new {@link ClassLoader} for the specified {@link SpringlifyPlugin}.
   *
   * @param plugin the Springlify plugin instance
   * @return the created class loader
   */
  @NotNull
  ClassLoader createClassLoader(@NotNull SpringlifyPlugin plugin);

}
