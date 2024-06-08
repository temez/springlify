package dev.temez.springlify.starter.initializer.loader;

import dev.temez.springlify.starter.plugin.SpringlifyPlugin;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

/**
 * Factory for creating a compound class loader for Springlify plugins.
 *
 * <p>This class implements {@link ClassLoaderFactory} and provides a method for creating a {@link CompoundClassLoader}
 * that combines multiple class loaders. The created class loader includes the class loader of the plugin
 * and the context class loader of the current thread.</p>
 *
 * @see ClassLoaderFactory
 * @see CompoundClassLoader
 * @see SpringlifyPlugin
 * @since 0.7.0.0-RC1
 */
public final class CompoundClassLoaderFactory implements ClassLoaderFactory {

  /**
   * {@inheritDoc}
   *
   * @param plugin the Springlify plugin instance
   * @return the created compound class loader
   */
  @Override
  public @NotNull ClassLoader createClassLoader(@NotNull SpringlifyPlugin plugin) {
    List<ClassLoader> loaders = new ArrayList<>(2);
    loaders.add(plugin.getClass().getClassLoader());
    loaders.add(Thread.currentThread().getContextClassLoader());
    return new CompoundClassLoader(loaders);
  }
}
