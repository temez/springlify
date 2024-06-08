package dev.temez.springlify.commander.command.discoverer.provider;

import dev.temez.springlify.commander.command.Command;
import dev.temez.springlify.commander.exception.discovery.CommandDiscoveryException;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Unmodifiable;

import java.util.List;

/**
 * Interface for providing command discovery from classes.
 *
 * @since 0.7.0.0-RC1
 */
public interface CommandDiscoveryProvider {

  /**
   * Checks if this provider supports the given command class.
   *
   * @param commandClass The class to check.
   * @return {@code true} if this provider supports the given class, {@code false} otherwise.
   */
  boolean supports(@NotNull Class<?> commandClass);

  /**
   * Discovers and creates a list of {@link Command} objects from the given command class.
   *
   * @param commandClass The class containing the command logic.
   * @return An unmodifiable list of discovered commands.
   * @throws CommandDiscoveryException If an error occurs during command discovery.
   */
  @NotNull
  @Unmodifiable
  List<Command> discover(@NotNull Class<?> commandClass) throws CommandDiscoveryException;
}
