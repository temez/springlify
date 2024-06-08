package dev.temez.springlify.commander.command.discoverer;

import dev.temez.springlify.commander.command.Command;
import dev.temez.springlify.commander.exception.discovery.CommandDiscoveryException;
import org.jetbrains.annotations.NotNull;

import java.util.List;

/**
 * Interface for discovering subcommands associated with an executor.
 *
 * @since 0.7.0.0-RC1
 */
public interface SubcommandDiscoverer {

  /**
   * Discovers subcommands associated with the given executor.
   *
   * @param executor the executor for which to discover subcommands
   * @return a list of discovered subcommands
   * @throws CommandDiscoveryException if an error occurs during subcommand discovery
   */
  @NotNull List<Command> discover(@NotNull Object executor) throws CommandDiscoveryException;
}
