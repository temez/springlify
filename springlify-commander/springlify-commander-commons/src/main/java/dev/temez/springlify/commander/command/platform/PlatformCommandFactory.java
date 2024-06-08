package dev.temez.springlify.commander.command.platform;

import dev.temez.springlify.commander.command.Command;
import org.jetbrains.annotations.NotNull;

/**
 * Generic factory interface for creating platform-specific commands.
 *
 * @param <T> The type of platform-specific command to create.
 * @since 0.7.0.0-RC1
 */
public interface PlatformCommandFactory<T> {

  /**
   * Creates a platform-specific command for the given registered command.
   *
   * @param registeredCommand The registered command.
   * @return A platform-specific command of type T.
   */
  @NotNull
  T create(@NotNull Command registeredCommand);
}

