package dev.temez.springlify.commander.commons.command.factory;

import dev.temez.springlify.commander.commons.command.RegisteredCommand;
import dev.temez.springlify.platform.commons.exception.FormattedException;
import org.jetbrains.annotations.NotNull;

/**
 * Factory interface for creating RegisteredCommand instances from command objects.
 *
 * @since 0.5.8.9dev
 */
public interface CommandFactory {

  /**
   * Creates a RegisteredCommand instance from the given command object.
   *
   * @param command The command object to create a RegisteredCommand for.
   * @return The created RegisteredCommand instance.
   * @throws FormattedException If an error occurs during the creation process.
   */
  @NotNull
  RegisteredCommand get(@NotNull Object command) throws FormattedException;
}
