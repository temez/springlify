package dev.temez.springlify.starter.plugin;

import com.velocitypowered.api.event.Subscribe;
import com.velocitypowered.api.event.proxy.ProxyInitializeEvent;
import com.velocitypowered.api.event.proxy.ProxyShutdownEvent;
import com.velocitypowered.api.proxy.ProxyServer;
import dev.temez.springlify.starter.configuration.ConfigurationLoader;
import dev.temez.springlify.starter.configuration.ConfigurationLoaderImpl;
import dev.temez.springlify.starter.initializer.SpringlifyInitializer;
import dev.temez.springlify.starter.initializer.SpringlifyInitializerImpl;
import dev.temez.springlify.starter.initializer.loader.ClassLoaderFactory;
import dev.temez.springlify.starter.initializer.loader.CompoundClassLoaderFactory;
import dev.temez.springlify.starter.server.ServerPlatformAdapter;
import dev.temez.springlify.starter.server.VelocityServerPlatformAdapter;
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
@FieldDefaults(makeFinal = true, level = AccessLevel.PROTECTED)
public abstract class SpringlifyVelocityPlugin implements SpringlifyPlugin {

  @Getter
  @NotNull
  ServerPlatformAdapter serverPlatformAdapter = new VelocityServerPlatformAdapter(this);

  @NotNull
  ClassLoaderFactory classLoaderFactory = new CompoundClassLoaderFactory();

  @NotNull
  ConfigurationLoader configurationLoader = new ConfigurationLoaderImpl();

  @Getter
  @NotNull
  SpringlifyInitializer initializer = new SpringlifyInitializerImpl(classLoaderFactory, configurationLoader);
  @Getter
  @NotNull
  ProxyServer server;
  @NotNull
  Path dataFolder;
  @Getter
  @NonFinal
  ConfigurableApplicationContext context;

  protected SpringlifyVelocityPlugin(@NotNull ProxyServer server, @NotNull Path dataFolder) {
    this.server = server;
    this.dataFolder = dataFolder;
  }

  @Subscribe
  public void onProxyInitialization(@NotNull ProxyInitializeEvent ignoredEvent) {
    initialize();
  }

  @Subscribe
  public void onProxyShutdown(@NotNull ProxyShutdownEvent ignoredEvent) {
    shutdown();
  }

  public @NotNull File getDataFolder() {
    return dataFolder.toFile();
  }
}
