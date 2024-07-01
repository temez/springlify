package dev.temez.springlify.platform.configuration;

import dev.temez.springlify.platform.server.BukkitServerPlatformAdapter;
import dev.temez.springlify.platform.server.ServerPlatformAdapter;
import org.bukkit.Bukkit;
import org.bukkit.Server;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitScheduler;
import org.jetbrains.annotations.NotNull;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Spring configuration class for Bukkit plugins using the Springlify framework.
 *
 * <p>This class provides the necessary Spring beans for interacting with the Bukkit API,
 * including the {@link PluginManager}, {@link Server}, and {@link ConsoleCommandSender}.</p>
 *
 * @see PluginManager
 * @see Server
 * @see ConsoleCommandSender
 * @since 0.7.0.0-RC1
 */
@Configuration
public class BukkitPluginConfiguration {

  /**
   * Provides the Bukkit {@link PluginManager} as a Spring bean.
   *
   * @return the Bukkit plugin manager
   */
  @Bean
  PluginManager pluginManager() {
    return Bukkit.getPluginManager();
  }

  /**
   * Provides the Bukkit {@link Server} as a Spring bean.
   *
   * @return the Bukkit server
   */
  @Bean
  Server server() {
    return Bukkit.getServer();
  }

  /**
   * Provides the Bukkit {@link ConsoleCommandSender} as a Spring bean.
   *
   * @return the Bukkit console command sender
   */
  @Bean
  ConsoleCommandSender consoleCommandSender() {
    return Bukkit.getConsoleSender();
  }

  /**
   * Provides the {@link BukkitScheduler} as a Spring bean.
   *
   * @return the BukkitScheduler
   */
  @Bean
  BukkitScheduler bukkitScheduler() {
    return Bukkit.getScheduler();
  }

  /**
   * Provides the {@link ServerPlatformAdapter} as a Spring bean.
   *
   * @param plugin        the Springlify plugin instance
   * @param pluginManager the plugin manager instance.
   * @return the server platform adapter
   */
  @Bean
  @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
  ServerPlatformAdapter serverPlatformAdapter(
      @NotNull JavaPlugin plugin,
      @NotNull PluginManager pluginManager
  ) {
    return new BukkitServerPlatformAdapter(plugin, pluginManager);
  }

}
