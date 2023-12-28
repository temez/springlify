package dev.temez.springlify.commander.commons.sender;

import org.jetbrains.annotations.NotNull;

/**
 * Interface representing a factory for obtaining details about a Commander plugin sender.
 *
 * @param <T> The type of details to be obtained.
 * @since 0.5.8.9dev
 */
public interface SenderDetailsFactory<T> {

  /**
   * Gets details or additional information about the specified Commander plugin sender.
   *
   * @param commandSender The Commander plugin sender.
   * @return Details or additional information about the sender.
   */
  @NotNull T get(@NotNull Sender<?> commandSender);
}
