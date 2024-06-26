package dev.temez.springlify.platform.server;

import org.jetbrains.annotations.NotNull;

/**
 * Interface for adapting different server platforms to the Springlify framework.
 *
 * <p>This interface defines methods for registering and unregistering event listeners.
 * Implementations of this interface will handle the specific logic for their respective
 * server platforms.</p>
 *
 * @since 0.7.0.0-RC1
 */
public interface ServerPlatformAdapter {

  /**
   * Registers the specified event listener with the server platform.
   *
   * @param listener the event listener to be registered
   */
  void registerEventListener(@NotNull Object listener);

  /**
   * Unregisters the specified event listener from the server platform.
   *
   * @param listener the event listener to be unregistered
   */
  void unregisterEventListener(@NotNull Object listener);

  /**
   * Registers a command executor with the platform (e.g., a server) associated with the plugin.
   *
   * @param command         The command to register.
   * @param commandExecutor The command executor.
   * @param alias           The command aliases.
   */
  void registerCommandExecutor(@NotNull String command, @NotNull Object commandExecutor, @NotNull String... alias);
}
