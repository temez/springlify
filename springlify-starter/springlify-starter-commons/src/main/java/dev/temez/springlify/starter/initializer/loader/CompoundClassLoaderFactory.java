package dev.temez.springlify.starter.initializer.loader;

import dev.temez.springlify.starter.plugin.SpringlifyPlugin;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public final class CompoundClassLoaderFactory implements ClassLoaderFactory {

  @Override
  public @NotNull ClassLoader createClassLoader(@NotNull SpringlifyPlugin plugin) {
    List<ClassLoader> loaders = new ArrayList<>(2);
    loaders.add(plugin.getClass().getClassLoader());
    loaders.add(Thread.currentThread().getContextClassLoader());
    return new CompoundClassLoader(loaders);
  }
}
