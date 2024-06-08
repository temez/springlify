package dev.temez.springlify.starter.configuration;

import org.bukkit.Bukkit;
import org.bukkit.Server;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.plugin.PluginManager;
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
public class SpringlifyBukkitPluginConfiguration {

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
}
