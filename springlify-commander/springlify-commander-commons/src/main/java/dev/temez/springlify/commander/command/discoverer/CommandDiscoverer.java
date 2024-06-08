package dev.temez.springlify.commander.command.discoverer;

import dev.temez.springlify.commander.command.Command;
import dev.temez.springlify.commander.exception.discovery.CommandDiscoveryException;
import org.jetbrains.annotations.NotNull;

/**
 * Interface for discovering commands from executor classes.
 *
 * @since 0.7.0.0-RC1
 */
public interface CommandDiscoverer {

  /**
   * Discovers and creates a {@link Command} from the given executor class.
   *
   * @param executorClass The class containing the command execution logic.
   * @return The discovered command.
   * @throws CommandDiscoveryException If an error occurs during command discovery.
   */
  @NotNull
  Command discoverCommand(@NotNull Class<?> executorClass) throws CommandDiscoveryException;

  /**
   * Checks if this discoverer supports the given executor class.
   *
   * @param executorClass The class to check.
   * @return {@code true} if this discoverer supports the given class, {@code false} otherwise.
   */
  boolean supports(@NotNull Class<?> executorClass);
}
