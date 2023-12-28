package dev.temez.springlify.commander.commons.command.platform;

import dev.temez.springlify.commander.commons.command.RegisteredCommand;
import org.jetbrains.annotations.NotNull;

/**
 * Factory interface for creating platform-specific command instances based on registered commands.
 *
 * @since 0.5.8.9dev
 */
public interface PlatformCommandFactory {

  /**
   * Creates a platform-specific command instance based on the given registered command.
   *
   * @param registeredCommand The registered command to create a platform-specific command for.
   * @return The created platform-specific command instance.
   */
  @NotNull
  PlatformCommand create(@NotNull RegisteredCommand registeredCommand);
}
