package dev.temez.springlify.commander.commons.command.registry;

import dev.temez.springlify.commander.commons.command.RegisteredCommand;
import dev.temez.springlify.platform.commons.exception.FormattedException;
import java.util.List;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Unmodifiable;

/**
 * Interface representing a registry for Commander plugin commands.
 *
 * @since 0.5.8.9dev
 */
public interface CommandRegistry {

  /**
   * Registers a command executor.
   *
   * @param commandExecutor The object representing the command executor.
   * @throws FormattedException If an error occurs during registration.
   */
  void register(@NotNull Object commandExecutor) throws FormattedException;

  /**
   * Gets a registered command by name.
   *
   * @param name The name of the command.
   * @return The registered command.
   * @throws FormattedException If the command with the specified name is not found.
   */
  @NotNull RegisteredCommand get(@NotNull String name) throws FormattedException;

  /**
   * Gets a list of all registered commands.
   *
   * @return The list of registered commands.
   */
  @NotNull
  @Unmodifiable
  List<RegisteredCommand> getRegisteredCommands();
}
