package dev.temez.springlify.starter.server;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.bukkit.event.HandlerList;
import org.bukkit.event.Listener;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;

/**
 * Implementation of {@link ServerPlatformAdapter} for the Bukkit server platform.
 *
 * <p>This class registers and unregisters event listeners with the Bukkit {@link PluginManager}.
 * The class uses Lombok annotations to generate constructors and manage field defaults.</p>
 *
 * @see ServerPlatformAdapter
 * @see Listener
 * @see PluginManager
 * @see HandlerList
 * @see JavaPlugin
 * @since 0.7.0.0-RC1
 */
@Slf4j
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public final class BukkitServerPlatformAdapter implements ServerPlatformAdapter {

  @NotNull
  JavaPlugin plugin;

  @NotNull
  PluginManager pluginManager;

  /**
   * {@inheritDoc}
   *
   * @param listener the event listener to be registered
   */
  @Override
  public void registerEventListener(@NotNull Object listener) {
    pluginManager.registerEvents((Listener) listener, plugin);
    log.debug("Registered {} as an event listener.", listener.getClass().getSimpleName());
  }

  /**
   * {@inheritDoc}
   *
   * @param listener the event listener to be unregistered
   */
  @Override
  public void unregisterEventListener(@NotNull Object listener) {
    HandlerList.unregisterAll((Listener) listener);
    log.debug("Unregistered event listener: {}", listener.getClass().getSimpleName());
  }
}
