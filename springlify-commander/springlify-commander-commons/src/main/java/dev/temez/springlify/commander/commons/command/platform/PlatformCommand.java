package dev.temez.springlify.commander.commons.command.platform;

import org.jetbrains.annotations.NotNull;

/**
 * Represents a command that can be registered on a platform.
 *
 * @since 0.5.8.9dev
 */
public interface PlatformCommand {

  /**
   * Registers the command on the platform with the specified arguments.
   *
   * @param args The arguments needed for registering the command.
   */
  void register(Object @NotNull ... args);
}
