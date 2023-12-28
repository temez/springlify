package dev.temez.springlify.velocity.configuration;

import com.velocitypowered.api.proxy.ProxyServer;
import dev.temez.springlify.velocity.plugin.SpringlifyVelocityPlugin;
import org.jetbrains.annotations.NotNull;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GenericPluginConfiguration {

  @Bean
  @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
  ProxyServer proxyServer(@NotNull SpringlifyVelocityPlugin plugin) {
    return plugin.getServer();
  }
}
