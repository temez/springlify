package dev.temez.springlify.commander.command.filter;

import dev.temez.springlify.commander.command.Command;
import dev.temez.springlify.commander.command.filter.provider.CommandFilteringProvider;
import dev.temez.springlify.commander.command.sender.Sender;
import dev.temez.springlify.commander.exception.filter.CommandFilterException;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class ProviderCommandFilterService implements CommandFilterService {

  @NotNull
  List<CommandFilteringProvider> providers;

  @Override
  public void filter(@NotNull Sender<?> sender, @NotNull Command command) throws CommandFilterException {
    providers.stream()
        .filter(provider -> provider.supports(command))
        .forEach(provider -> provider.filter(sender, command));
  }

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
