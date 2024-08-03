package dev.temez.springlify.starter.plugin;

import com.google.common.eventbus.Subscribe;
import com.velocitypowered.api.event.proxy.ProxyInitializeEvent;
import com.velocitypowered.api.event.proxy.ProxyShutdownEvent;
import com.velocitypowered.api.proxy.ProxyServer;
import dev.temez.springlify.starter.configuration.loader.ConfigurationLoader;
import dev.temez.springlify.starter.configuration.loader.ConfigurationLoaderImpl;
import dev.temez.springlify.starter.initializer.SpringlifyInitializer;
import dev.temez.springlify.starter.initializer.SpringlifyInitializerImpl;
import dev.temez.springlify.starter.initializer.loader.ClassLoaderFactory;
import dev.temez.springlify.starter.initializer.loader.CompoundClassLoaderFactory;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;
import lombok.experimental.NonFinal;
import org.jetbrains.annotations.NotNull;
import org.springframework.context.ConfigurableApplicationContext;

import java.io.File;
import java.nio.file.Path;

@Setter
@Getter
@FieldDefaults(makeFinal = true, level = AccessLevel.PROTECTED)
public class SpringlifyVelocityPlugin implements SpringlifyPlugin {

  @NotNull
  ClassLoaderFactory classLoaderFactory = new CompoundClassLoaderFactory();

  @NotNull
  ConfigurationLoader configurationLoader = new ConfigurationLoaderImpl();

  @NotNull
  SpringlifyInitializer initializer = new SpringlifyInitializerImpl(classLoaderFactory, configurationLoader);

  @NonFinal
  ConfigurableApplicationContext context;

  @NotNull
  ProxyServer server;

  @NotNull
  Path dataFolder;

  public SpringlifyVelocityPlugin(@NotNull ProxyServer server, @NotNull Path dataFolder) {
    this.server = server;
    this.dataFolder = dataFolder;
  }

  @Subscribe
  public void onProxyInitialization(@NotNull ProxyInitializeEvent event) {
    initialize();
  }

  @Subscribe
  public void onProxyShutdown(@NotNull ProxyShutdownEvent event) {
    shutdown();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public ClassLoader getPluginClassLoader() {
    // if this don't work, try to refactor this to return the actual class loader
    return this.getClass().getClassLoader();
  }
  
  public @NotNull File getDataFolder() {
    return dataFolder.toFile();
  }
}
