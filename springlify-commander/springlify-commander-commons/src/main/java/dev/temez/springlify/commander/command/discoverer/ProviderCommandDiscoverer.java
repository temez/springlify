package dev.temez.springlify.commander.command.discoverer;

import dev.temez.springlify.commander.command.Command;
import dev.temez.springlify.commander.command.discoverer.provider.CommandDiscoveryProvider;
import dev.temez.springlify.commander.exception.discovery.CommandDiscoveryException;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Implementation of {@link CommandDiscoverer} that uses a {@link CommandDiscoveryProvider}.
 *
 * @since 0.7.0.0-RC1
 */
@Component
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public final class ProviderCommandDiscoverer implements CommandDiscoverer {

  @NotNull
  @Qualifier("annotationClassCommandDiscoveryProvider")
  CommandDiscoveryProvider commandDiscoveryProvider;

  /**
   * Discovers and creates a {@link Command} from the given executor class.
   * Uses the provided {@link CommandDiscoveryProvider} to perform the discovery.
   *
   * @param executorClass The class containing the command execution logic.
   * @return The discovered command.
   * @throws CommandDiscoveryException If no command or multiple commands are found for the given class.
   */
  @Override
  public @NotNull Command discoverCommand(@NotNull Class<?> executorClass) throws CommandDiscoveryException {
    List<Command> commandList = commandDiscoveryProvider.discover(executorClass);
    if (commandList.isEmpty()) {
      throw new CommandDiscoveryException("No command found for executor class " + executorClass.getName());
    }
    if (commandList.size() > 1) {
      throw new CommandDiscoveryException("Multiple commands found for executor class " + executorClass.getName());
    }
    return commandList.get(0);
  }

  /**
   * Checks if this discoverer supports the given executor class.
   * Delegates the check to the provided {@link CommandDiscoveryProvider}.
   *
   * @param executorClass The class to check.
   * @return {@code true} if this discoverer supports the given class, {@code false} otherwise.
   */
  @Override
  public boolean supports(@NotNull Class<?> executorClass) {
    return commandDiscoveryProvider.supports(executorClass);
  }
}
