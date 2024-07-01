package dev.temez.springlify.platform.server;

import com.velocitypowered.api.command.Command;
import com.velocitypowered.api.command.CommandManager;
import com.velocitypowered.api.event.EventManager;
import dev.temez.springlify.starter.plugin.SpringlifyVelocityPlugin;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class VelocityServerPlatformAdapter implements ServerPlatformAdapter {

  @NotNull
  EventManager eventManager;

  @NotNull
  CommandManager commandManager;

  @NotNull
  SpringlifyVelocityPlugin plugin;

  @Override
  public void registerEventListener(@NotNull Object listener) {
    eventManager.register(plugin, listener);
    log.debug("Registered {} as an event listener", listener.getClass().getSimpleName());

  }

  @Override
  public void unregisterEventListener(@NotNull Object listener) {
    eventManager.unregisterListener(plugin, listener);
    log.debug("Unregistered event listener: {}", listener.getClass().getSimpleName());

  }

  @Override
  public void registerCommandExecutor(@NotNull String command, @NotNull Object commandExecutor, @NotNull String... alias) {
    commandManager.register(command, (Command) commandExecutor, alias);
    log.debug("Registered {} as a command executor", commandExecutor.getClass().getSimpleName());
  }
}
