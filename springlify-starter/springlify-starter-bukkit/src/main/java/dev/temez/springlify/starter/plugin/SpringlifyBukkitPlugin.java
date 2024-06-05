package dev.temez.springlify.starter.plugin;

import dev.temez.springlify.starter.configuration.ConfigurationLoader;
import dev.temez.springlify.starter.configuration.ConfigurationLoaderImpl;
import dev.temez.springlify.starter.initializer.SpringlifyInitializer;
import dev.temez.springlify.starter.initializer.SpringlifyInitializerImpl;
import dev.temez.springlify.starter.initializer.loader.ClassLoaderFactory;
import dev.temez.springlify.starter.initializer.loader.CompoundClassLoaderFactory;
import dev.temez.springlify.starter.server.BukkitServerPlatformAdapter;
import dev.temez.springlify.starter.server.ServerPlatformAdapter;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;
import lombok.experimental.NonFinal;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;
import org.springframework.context.ConfigurableApplicationContext;

@Setter
@Getter
@FieldDefaults(makeFinal = true, level = AccessLevel.PROTECTED)
public abstract class SpringlifyBukkitPlugin extends JavaPlugin implements SpringlifyPlugin {

  @NotNull
  ServerPlatformAdapter serverPlatformAdapter = new BukkitServerPlatformAdapter(this);

  @NotNull
  ClassLoaderFactory classLoaderFactory = new CompoundClassLoaderFactory();

  @NotNull
  ConfigurationLoader configurationLoader = new ConfigurationLoaderImpl();

  @NotNull
  SpringlifyInitializer initializer = new SpringlifyInitializerImpl(classLoaderFactory, configurationLoader);

  @NonFinal
  ConfigurableApplicationContext context;

  @Override
  public void onEnable() {
    initialize();
  }

  @Override
  public void onDisable() {
    shutdown();
  }
}
