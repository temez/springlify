package dev.temez.springlify.starter.server;

import dev.temez.springlify.starter.plugin.SpringlifyVelocityPlugin;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;

@Slf4j
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PROTECTED)
public final class VelocityServerPlatformAdapter implements ServerPlatformAdapter {

  @NotNull
  SpringlifyVelocityPlugin plugin;

  @Override
  public void registerEventListener(@NotNull Object listener) {
    plugin.getServer().getEventManager().register(plugin, listener);
    log.debug("Registered {} as an event listener.", listener.getClass().getSimpleName());
  }

  @Override
  public void unregisterEventListener(@NotNull Object listener) {
    plugin.getServer().getEventManager().unregisterListener(plugin, listener);
    log.debug("Unregistered event listener: {}", listener.getClass().getSimpleName());
  }

}