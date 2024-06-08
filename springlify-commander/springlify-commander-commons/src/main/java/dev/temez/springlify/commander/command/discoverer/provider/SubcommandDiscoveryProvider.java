package dev.temez.springlify.commander.command.discoverer.provider;

import dev.temez.springlify.commander.command.Command;
import dev.temez.springlify.commander.exception.discovery.CommandDiscoveryException;
import org.jetbrains.annotations.NotNull;

import java.util.List;

/**
 * Interface for providing subcommand discovery based on an executor.
 *
 * @since 0.7.0.0-RC1
 */
public interface SubcommandDiscoveryProvider {

  /**
   * Checks if the provider supports discovering subcommands for the given executor.
   *
   * @param executor the executor to check support for
   * @return {@code true} if the provider supports the executor, {@code false} otherwise
   */
  boolean supports(@NotNull Object executor);

  /**
   * Discovers subcommands associated with the given executor.
   *
   * @param executor the executor for which to discover subcommands
   * @return a list of discovered subcommands
   * @throws CommandDiscoveryException if an error occurs during subcommand discovery
   */
  @NotNull List<Command> discover(@NotNull Object executor) throws CommandDiscoveryException;
}
