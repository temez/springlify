package dev.temez.springlify.velocity.plugin;

import com.velocitypowered.api.event.Subscribe;
import com.velocitypowered.api.event.proxy.ProxyInitializeEvent;
import com.velocitypowered.api.event.proxy.ProxyShutdownEvent;
import com.velocitypowered.api.proxy.ProxyServer;
import dev.temez.springlify.starter.commons.event.SpringlifyEventPublisher;
import dev.temez.springlify.starter.commons.event.impl.ContextPreShutdownEvent;
import dev.temez.springlify.starter.commons.plugin.SpringlifyPlugin;
import dev.temez.springlify.starter.commons.server.ServerPlatformAdapter;
import dev.temez.springlify.velocity.platform.VelocityServerPlatformAdapter;
import java.io.File;
import java.nio.file.Path;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.FieldDefaults;
import lombok.experimental.NonFinal;
import org.checkerframework.checker.nullness.qual.MonotonicNonNull;
import org.jetbrains.annotations.NotNull;
import org.springframework.context.ConfigurableApplicationContext;

/**
 * The {@code SpringlifyVelocityPlugin} abstract class serves as a base class for Velocity plugins
 * that integrate with the Spring framework.
 * It provides common functionality for initializing and shutting down the plugin
 * while managing a Spring application context.
 *
 * <p>Implementations of Velocity plugins that utilize Spring should extend this class and provide
 * the necessary configuration by overriding methods.
 */
@Getter
@SuppressWarnings("unused")
@FieldDefaults(makeFinal = true, level = AccessLevel.PROTECTED)
public abstract class SpringlifyVelocityPlugin implements SpringlifyPlugin {

  @NotNull
  ProxyServer server;

  @NotNull
  Path dataFolder;

  @NonFinal
  @MonotonicNonNull
  private ConfigurableApplicationContext context;

  /**
   * Constructs a new instance of {@code SpringlifyVelocityPlugin}.
   *
   * @param server     The Velocity proxy server.
   * @param dataFolder The data folder for the plugin.
   */
  public SpringlifyVelocityPlugin(@NotNull ProxyServer server, @NotNull Path dataFolder) {
    this.server = server;
    this.dataFolder = dataFolder;
  }

  /**
   * Gets the data folder for the plugin.
   *
   * @return The data folder.
   */
  @Override
  public @NotNull File getDataFolder() {
    return dataFolder.toFile();
  }

  /**
   * Initializes the plugin. This method should be called during plugin startup.
   * It initializes the Spring application context and performs any necessary setup.
   */
  @Override
  public void initialize() {
    context = getInitializer().initialize(this);
  }

  /**
   * Shuts down the plugin. This method should be called during plugin shutdown.
   * It closes the Spring application context if it was initialized and also shuts down the
   * Velocity server.
   */
  @Override
  public void shutdown() {
    if (context != null) {
      context.getBean(SpringlifyEventPublisher.class)
          .publish(new ContextPreShutdownEvent(this));
      context.close();
    }
  }

  @Override
  public @NotNull ServerPlatformAdapter getServerPlatformAdapter() {
    return new VelocityServerPlatformAdapter(this);
  }

  /**
   * Handles the proxy initialization event by calling the {@link #initialize()} method.
   *
   * @param ignoredEvent The proxy initialization event.
   */
  @Subscribe
  public void onProxyInitialization(@NotNull ProxyInitializeEvent ignoredEvent) {
    initialize();
  }

  /**
   * Handles the proxy shutdown event by calling the {@link #shutdown()} method.
   *
   * @param ignoredEvent The proxy shutdown event.
   */
  @Subscribe
  public void onProxyShutdown(@NotNull ProxyShutdownEvent ignoredEvent) {
    shutdown();
  }
}
