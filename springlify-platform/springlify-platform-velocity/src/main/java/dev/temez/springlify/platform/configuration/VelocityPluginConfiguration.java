package dev.temez.springlify.platform.configuration;

import com.velocitypowered.api.command.CommandManager;
import com.velocitypowered.api.event.EventManager;
import com.velocitypowered.api.plugin.PluginManager;
import com.velocitypowered.api.proxy.ConsoleCommandSource;
import com.velocitypowered.api.proxy.ProxyServer;
import com.velocitypowered.api.scheduler.Scheduler;
import dev.temez.springlify.starter.plugin.SpringlifyVelocityPlugin;
import org.jetbrains.annotations.NotNull;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class VelocityPluginConfiguration {

  @Bean
  @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
  ProxyServer proxyServer(@NotNull SpringlifyVelocityPlugin plugin) {
    return plugin.getServer();
  }

  @Bean
  ConsoleCommandSource consoleCommandSource(@NotNull ProxyServer proxyServer) {
    return proxyServer.getConsoleCommandSource();
  }

  @Bean
  PluginManager pluginManager(@NotNull ProxyServer proxyServer) {
    return proxyServer.getPluginManager();
  }

  @Bean
  EventManager eventManager(@NotNull ProxyServer proxyServer) {
    return proxyServer.getEventManager();
  }

  @Bean
  CommandManager commandManager(@NotNull ProxyServer proxyServer) {
    return proxyServer.getCommandManager();
  }

  @Bean
  Scheduler scheduler(@NotNull ProxyServer proxyServer) {
    return proxyServer.getScheduler();
  }
}
