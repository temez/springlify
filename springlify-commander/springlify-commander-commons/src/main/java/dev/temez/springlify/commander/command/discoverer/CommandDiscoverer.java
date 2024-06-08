package dev.temez.springlify.commander.command.discoverer;

import dev.temez.springlify.commander.command.Command;
import dev.temez.springlify.commander.exception.discovery.CommandDiscoveryException;
import org.jetbrains.annotations.NotNull;

/**
 * Interface for discovering commands.
 *
 * @since 0.7.0.0-RC1
 */
public interface CommandDiscoverer {

  /**
   * Discovers the command associated with the given executor.
   *
   * @param executor the executor for which to discover the command
   * @return the discovered command
   * @throws CommandDiscoveryException if an error occurs during command discovery
   */
  @NotNull Command discover(@NotNull Object executor) throws CommandDiscoveryException;
}
