package dev.temez.springlify.commander.commons.sender;

import java.util.UUID;
import org.jetbrains.annotations.NotNull;

/**
 * Interface representing a sender of commands or messages within a Commander plugin.
 *
 * @param <T> The type of the platform-specific sender.
 * @since 0.5.8.9dev
 */
public interface Sender<T> {

  /**
   * Gets the platform-specific sender.
   *
   * @return The platform-specific sender.
   */
  @NotNull T getPlatformSender();

  /**
   * Gets the platform-specific sender uuid.
   *
   * @return The platform-specific sender uuid.
   */
  @NotNull UUID getUuid() throws IllegalArgumentException;

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
