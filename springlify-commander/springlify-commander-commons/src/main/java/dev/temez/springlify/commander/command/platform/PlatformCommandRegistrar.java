package dev.temez.springlify.commander.command.platform;

import org.jetbrains.annotations.NotNull;

/**
 * Generic interface for registering platform-specific commands.
 *
 * @param <T> The type of platform-specific command to register.
 * @since 0.7.0.0-RC1
 */
public interface PlatformCommandRegistrar<T> {

  /**
   * Registers the platform-specific command.
   *
   * @param platformCommand The platform-specific command to register.
   */
  void register(@NotNull T platformCommand);
}
