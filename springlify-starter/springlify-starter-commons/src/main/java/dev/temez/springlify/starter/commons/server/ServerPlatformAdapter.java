package dev.temez.springlify.starter.commons.server;

import org.jetbrains.annotations.NotNull;


/**
 * The {@code ServerPlatformAdapter} interface defines methods that must be implemented
 * by platform-specific classes.
 */
public interface ServerPlatformAdapter {

  /**
   * Registers a listener with the platform (e.g., a server) associated with the plugin.
   *
   * @param listener The listener to register.
   */
  void registerListener(@NotNull Object listener);

  /**
   * Unregisters a listener from the platform (e.g., a server) associated with the plugin.
   *
   * @param listener The listener to unregister.
   */
  void unregisterListener(@NotNull Object listener);

  /**
   * Registers a command executor with the platform (e.g., a server) associated with the plugin.
   *
   * @param command         The command to register.
   * @param commandExecutor The command executor.
   * @param alias           The command aliases.
   */
  void registerCommandExecutor(
      @NotNull String command,
      @NotNull Object commandExecutor,
      @NotNull String... alias
  );

}
