package dev.temez.springlify.platform.velocity.platform;

import com.velocitypowered.api.command.Command;
import dev.temez.springlify.commons.server.ServerPlatformAdapter;
import dev.temez.springlify.platform.velocity.plugin.SpringlifyVelocityPlugin;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.log4j.Log4j2;
import org.jetbrains.annotations.NotNull;


/**
 * This class implements {@link ServerPlatformAdapter} interface and implements
 * Velocity proxy platform.
 */
@Log4j2
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PROTECTED)
public class VelocityServerPlatformAdapter implements ServerPlatformAdapter {

  @NotNull SpringlifyVelocityPlugin plugin;

  /**
   * Registers a listener with the Velocity server.
   *
   * @param listener The listener to register.
   */
  @Override
  public void registerListener(@NotNull Object listener) {
    plugin.getServer().getEventManager().register(this, listener);
    log.debug("Registered {} as an event listener.", listener.getClass().getSimpleName());
  }

  /**
   * Unregisters a listener from the Velocity server.
   *
   * @param listener The listener to unregister.
   */
  @Override
  public void unregisterListener(@NotNull Object listener) {
    plugin.getServer().getEventManager().unregisterListener(this, listener);
    log.debug("Unregistered event listener: {}", listener.getClass().getSimpleName());
  }

  /**
   * Registers a command executor with the Velocity server.
   *
   * @param command         The command to register.
   * @param commandExecutor The command executor.
   * @param alias           The command aliases.
   */
  @Override
  public void registerCommandExecutor(@NotNull String command, @NotNull Object commandExecutor,
                                      @NotNull String... alias) {
    plugin.getServer().getCommandManager().register(command, (Command) commandExecutor, alias);
    log.info(
        "Registered {} as a command executor.", commandExecutor.getClass().getSimpleName()
    );
  }
}
