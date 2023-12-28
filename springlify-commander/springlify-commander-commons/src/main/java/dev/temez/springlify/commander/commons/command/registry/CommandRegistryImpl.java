package dev.temez.springlify.commander.commons.command.registry;

import dev.temez.springlify.commander.commons.command.RegisteredCommand;
import dev.temez.springlify.commander.commons.command.factory.CommandFactory;
import dev.temez.springlify.commander.commons.command.platform.PlatformCommand;
import dev.temez.springlify.commander.commons.command.platform.PlatformCommandFactory;
import dev.temez.springlify.commander.commons.command.platform.PlatformCommandRegistrar;
import dev.temez.springlify.commander.commons.exception.FormattedException;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Unmodifiable;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public final class CommandRegistryImpl implements CommandRegistry {

  @NotNull HashMap<String, RegisteredCommand> registeredCommands = new HashMap<>();

  @NotNull CommandFactory commandFactory;

  @NotNull PlatformCommandFactory platformCommandFactory;

  @NotNull PlatformCommandRegistrar platformCommandRegistrar;

  @Override
  public void register(@NotNull Object commandExecutor) throws FormattedException {
    RegisteredCommand command = commandFactory.get(commandExecutor);
    registeredCommands.put(command.getName(), command);
    PlatformCommand platformCommand = platformCommandFactory.create(command);
    platformCommandRegistrar.register(platformCommand);
  }


  @Override
  public @NotNull RegisteredCommand get(@NotNull String name) throws FormattedException {
    return Optional
        .ofNullable(registeredCommands.get(name))
        .orElseThrow(() -> new FormattedException("Command with name %s not registered!", name));
  }

  @Override
  public @NotNull @Unmodifiable List<RegisteredCommand> getRegisteredCommands() {
    return List.copyOf(registeredCommands.values());
  }
}
