package dev.temez.springlify.commander.commons.command.platform;

import org.jetbrains.annotations.NotNull;

/**
 * Interface for registering platform-specific commands.
 *
 * @since 0.5.8.9dev
 */
public interface PlatformCommandRegistrar {

  /**
   * Registers the given platform-specific command on the platform.
   *
   * @param platformCommand The platform-specific command to register.
   */
  void register(@NotNull PlatformCommand platformCommand);
}
