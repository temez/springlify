package dev.temez.springlify.commander.command.discoverer;

import dev.temez.springlify.commander.command.Command;
import dev.temez.springlify.commander.command.discoverer.provider.SubcommandDiscoveryProvider;
import dev.temez.springlify.commander.exception.discovery.CommandDiscoveryException;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;

/**
 * Component for discovering subcommands using multiple providers.
 *
 * @since 0.7.0.0-RC1
 */
@Component
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public final class ProviderSubcommandDiscoverer implements SubcommandDiscoverer {

  /**
   * List of subcommand discovery providers.
   */
  @NotNull
  List<SubcommandDiscoveryProvider> providers;

  /**
   * Discovers subcommands associated with the given executor.
   *
   * @param executor the executor for which to discover subcommands
   * @return a list of discovered subcommands
   * @throws CommandDiscoveryException if an error occurs during subcommand discovery
   */
  @Override
  public @NotNull List<Command> discover(@NotNull Object executor) throws CommandDiscoveryException {
    return providers.stream()
        .filter(provider -> provider.supports(executor))
        .map(provider -> provider.discover(executor))
        .flatMap(Collection::stream)
        .toList();
  }
}
