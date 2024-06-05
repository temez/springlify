package dev.temez.springlify.starter.server;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.bukkit.Bukkit;
import org.bukkit.event.HandlerList;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;

@Slf4j
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public final class BukkitServerPlatformAdapter implements ServerPlatformAdapter {

  @NotNull
  JavaPlugin plugin;

  @Override
  public void registerEventListener(@NotNull Object listener) {
    Bukkit.getPluginManager().registerEvents((Listener) listener, plugin);
    log.debug("Registered {} as an event listener.", listener.getClass().getSimpleName());
  }

  @Override
  public void unregisterEventListener(@NotNull Object listener) {
    HandlerList.unregisterAll((Listener) listener);
    log.debug("Unregistered event listener: {}", listener.getClass().getSimpleName());
  }
}
