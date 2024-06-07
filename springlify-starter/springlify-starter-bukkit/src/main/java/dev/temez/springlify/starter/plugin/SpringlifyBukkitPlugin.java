package dev.temez.springlify.starter.plugin;

import dev.temez.springlify.starter.configuration.loader.ConfigurationLoader;
import dev.temez.springlify.starter.configuration.loader.ConfigurationLoaderImpl;
import dev.temez.springlify.starter.initializer.SpringlifyInitializer;
import dev.temez.springlify.starter.initializer.SpringlifyInitializerImpl;
import dev.temez.springlify.starter.initializer.loader.ClassLoaderFactory;
import dev.temez.springlify.starter.initializer.loader.CompoundClassLoaderFactory;
import dev.temez.springlify.starter.server.BukkitServerPlatformAdapter;
import dev.temez.springlify.starter.server.ServerPlatformAdapter;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;
import lombok.experimental.NonFinal;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;
import org.springframework.context.ConfigurableApplicationContext;

/**
 * Base class for Bukkit plugins using the Springlify.
 *
 * <p>This abstract class extends {@link JavaPlugin} and implements {@link SpringlifyPlugin}.
 * It initializes necessary components for integrating Spring with Bukkit, including
 * the {@link ServerPlatformAdapter}, {@link ClassLoaderFactory}, and {@link ConfigurationLoader}.
 * It also provides lifecycle management methods for enabling and disabling the plugin.</p>
 *
 * @see JavaPlugin
 * @see SpringlifyPlugin
 * @see ServerPlatformAdapter
 * @see ClassLoaderFactory
 * @see ConfigurationLoader
 * @since 0.7.0.0-RC1
 */
@Setter
@Getter
@FieldDefaults(makeFinal = true, level = AccessLevel.PROTECTED)
public abstract class SpringlifyBukkitPlugin extends JavaPlugin implements SpringlifyPlugin {

  @NotNull
  ServerPlatformAdapter serverPlatformAdapter = new BukkitServerPlatformAdapter(this, Bukkit.getPluginManager());

  @NotNull
  ClassLoaderFactory classLoaderFactory = new CompoundClassLoaderFactory();

  @NotNull
  ConfigurationLoader configurationLoader = new ConfigurationLoaderImpl();

  @NotNull
  SpringlifyInitializer initializer = new SpringlifyInitializerImpl(classLoaderFactory, configurationLoader);

  @NonFinal
  ConfigurableApplicationContext context;

  /**
   * {@inheritDoc}
   */
  @Override
  public void onEnable() {
    initialize();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void onDisable() {
    shutdown();
  }
}
