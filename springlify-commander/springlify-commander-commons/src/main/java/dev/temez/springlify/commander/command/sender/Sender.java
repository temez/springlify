package dev.temez.springlify.commander.command.sender;


import org.jetbrains.annotations.NotNull;

import java.util.UUID;

/**
 * Interface representing a sender of commands.
 *
 * @param <T> The type of the platform-specific sender.
 * @since 0.7.0.0-RC1
 */
public interface Sender<T> {

  /**
   * Retrieves the platform-specific sender object.
   *
   * @return The platform-specific sender object.
   */
  @NotNull T getPlatformSender();

  /**
   * Retrieves the UUID of the sender.
   *
   * @return The UUID of the sender.
   * @throws UnsupportedOperationException If UUID retrieval is not supported.
   */
  @NotNull UUID getUuid() throws UnsupportedOperationException;

  /**
   * Checks if the sender is a console sender.
   *
   * @return {@code true} if the sender is a console sender, {@code false} otherwise.
   */
  boolean isConsoleSender();

  /**
   * Checks if the sender has the specified permission.
   *
   * @param permission The permission to check.
   * @return {@code true} if the sender has the permission, {@code false} otherwise.
   */
  boolean hasPermission(@NotNull String permission);
}
