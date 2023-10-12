package dev.temez.springlify.platform.bukkit.plugin;

import dev.temez.springlify.commons.initializer.SpringlifyInitializer;
import dev.temez.springlify.commons.plugin.SpringlifyPlugin;
import dev.temez.springlify.commons.server.ServerPlatformAdapter;
import dev.temez.springlify.platform.bukkit.initializer.SpringlifyBukkitInitializer;
import dev.temez.springlify.platform.bukkit.server.BukkitServerPlatformAdapter;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.FieldDefaults;
import lombok.experimental.NonFinal;
import org.bukkit.plugin.java.JavaPlugin;
import org.checkerframework.checker.nullness.qual.MonotonicNonNull;
import org.jetbrains.annotations.NotNull;
import org.springframework.context.ConfigurableApplicationContext;

/**
 * The {@code SpringlifyBukkitPlugin} abstract class extends {@link JavaPlugin} and implements
 * {@link SpringlifyPlugin}. It provides common functionality
 * for integrating a Bukkit plugin with the Spring framework.
 */
@Getter
@FieldDefaults(makeFinal = true, level = AccessLevel.PROTECTED)
public abstract class SpringlifyBukkitPlugin extends JavaPlugin implements SpringlifyPlugin {

  /**
   * The Spring application context associated with the plugin.
   * It is lazily initialized during plugin startup.
   */
  @NonFinal
  @MonotonicNonNull
  private ConfigurableApplicationContext context;

  /**
   * Get the spring application initializer.
   *
   * @return The spring application initializer.
   */
  @Override
  public @NotNull SpringlifyInitializer getInitializer() {
    return new SpringlifyBukkitInitializer();
  }

  /**
   * Gets the server platform implementation.
   *
   * @return The server platform implementation.
   */
  @Override
  public @NotNull ServerPlatformAdapter getServerPlatformAdapter() {
    return new BukkitServerPlatformAdapter(this);
  }

  /**
   * Initializes the Bukkit plugin. This method should be called during plugin startup.
   * It initializes the Spring application context and performs any necessary setup.
   */
  @Override
  public void initialize() {
    context = getInitializer().initialize(this);
  }

  /**
   * Shuts down the Bukkit plugin. This method should be called during plugin shutdown.
   * It closes the Spring application context if it was initialized.
   */
  @Override
  public void shutdown() {
    if (context != null) {
      context.close();
    }
  }

  /**
   * Called when the plugin is disabled. Shuts down the plugin.
   */
  @Override
  public void onDisable() {
    shutdown();
  }

  /**
   * Called when the plugin is enabled. Initializes the plugin.
   */
  @Override
  public void onEnable() {
    initialize();
  }
}
