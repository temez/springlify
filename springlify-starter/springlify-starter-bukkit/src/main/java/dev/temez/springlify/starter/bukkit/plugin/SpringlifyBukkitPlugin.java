package dev.temez.springlify.starter.bukkit.plugin;

import dev.temez.springlify.starter.bukkit.server.BukkitServerPlatformAdapter;
import dev.temez.springlify.starter.commons.event.SpringlifyEventPublisher;
import dev.temez.springlify.starter.commons.event.impl.ContextPreShutdownEvent;
import dev.temez.springlify.starter.commons.plugin.SpringlifyPlugin;
import dev.temez.springlify.starter.commons.server.ServerPlatformAdapter;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.FieldDefaults;
import lombok.experimental.NonFinal;
import org.bukkit.plugin.java.JavaPlugin;
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
  private ConfigurableApplicationContext context;


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
      context.getBean(SpringlifyEventPublisher.class)
          .publish(new ContextPreShutdownEvent(this));
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

  @Override
  public void saveResource(@NotNull String resourcePath, boolean replace) {
    super.saveResource(resourcePath, replace);
  }
}
