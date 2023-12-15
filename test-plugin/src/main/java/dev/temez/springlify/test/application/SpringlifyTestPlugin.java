package dev.temez.springlify.test.application;

import dev.temez.springlify.platform.bukkit.plugin.SpringlifyBukkitPlugin;
import org.jetbrains.annotations.NotNull;

/**
 * The {@code SpringlifyTestPlugin} class extends {@code SpringlifyBukkitPlugin} and serves
 * as the main plugin class for the Springlify integration in a Bukkit-based environment.
 *
 * <p>This plugin class specifies the main application class using the {@code getApplicationClass}
 * method. The associated Spring Boot application is {@code SpringlifyTestApplication}.</p>
 *
 * @since 0.5.9.4dev
 */
public class SpringlifyTestPlugin extends SpringlifyBukkitPlugin {

  /**
   * Gets the main application class for the Spring Boot application associated with the plugin.
   *
   * @return The main application class.
   */
  @Override
  public @NotNull Class<?> getApplicationClass() {
    return SpringlifyTestApplication.class;
  }
}