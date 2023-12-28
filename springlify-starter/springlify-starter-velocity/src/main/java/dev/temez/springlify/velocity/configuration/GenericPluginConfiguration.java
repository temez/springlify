package dev.temez.springlify.velocity.configuration;

import com.velocitypowered.api.proxy.ProxyServer;
import dev.temez.springlify.velocity.plugin.SpringlifyVelocityPlugin;
import org.jetbrains.annotations.NotNull;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * The {@code GenericPluginConfiguration} class provides Spring configuration for the generic
 * aspects of the plugin, such as creating a bean for the Velocity ProxyServer.
 *
 * @since 0.5.9.8dev
 */
@Configuration
public class GenericPluginConfiguration {

  /**
   * Creates a bean for the Velocity ProxyServer using the provided SpringlifyVelocityPlugin.
   *
   * @param plugin The SpringlifyVelocityPlugin instance.
   * @return The Velocity ProxyServer bean.
   */
  @Bean
  @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
  ProxyServer proxyServer(@NotNull SpringlifyVelocityPlugin plugin) {
    return plugin.getServer();
  }
}