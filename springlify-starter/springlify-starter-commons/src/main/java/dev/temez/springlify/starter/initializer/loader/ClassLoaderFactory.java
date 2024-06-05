package dev.temez.springlify.starter.initializer.loader;

import dev.temez.springlify.starter.plugin.SpringlifyPlugin;
import org.jetbrains.annotations.NotNull;

public interface ClassLoaderFactory {

  @NotNull ClassLoader createClassLoader(@NotNull SpringlifyPlugin plugin);

}
