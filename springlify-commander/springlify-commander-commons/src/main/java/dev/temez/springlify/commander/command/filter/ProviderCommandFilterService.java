package dev.temez.springlify.commander.command.filter;

import dev.temez.springlify.commander.command.Command;
import dev.temez.springlify.commander.command.filter.provider.CommandFilterProvider;
import dev.temez.springlify.commander.command.sender.Sender;
import dev.temez.springlify.commander.exception.filter.CommandFilterException;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Service implementation for filtering command execution.
 *
 * @since 0.7.0.0-RC1
 */
@Service
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class ProviderCommandFilterService implements CommandFilterService {

  @NotNull
  List<CommandFilterProvider> providers;

  /**
   * Filters the command execution based on the sender and the command.
   *
   * @param sender  The sender executing the command.
   * @param command The command being executed.
   * @throws CommandFilterException If an error occurs during the filtering process.
   */
  @Override
  public void filter(@NotNull Sender<?> sender, @NotNull Command command) throws CommandFilterException {
    providers.stream()
        .filter(provider -> provider.supports(command))
        .forEach(provider -> provider.filter(sender, command));
  }

  /**
   * Checks if the sender has access to execute the given command.
   *
   * @param sender  The sender executing the command.
   * @param command The command to check accessibility for.
   * @return {@code true} if the sender has access to execute the command, {@code false} otherwise.
   */
  @Override
  public boolean isAccessible(@NotNull Sender<?> sender, @NotNull Command command) {
    try {
      filter(sender, command);
      return true;
    } catch (CommandFilterException exception) {
      return false;
    }
  }
}
