package dev.temez.springlify.commander.command.platform;

import dev.temez.springlify.commander.command.Command;
import dev.temez.springlify.commander.service.CommandService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public final class BukkitCommandFactory implements PlatformCommandFactory {

  @NotNull
  CommandService commandService;

  @Override
  public @NotNull PlatformCommand create(@NotNull Command registeredCommand) {

    return new CommanderBukkitCommand(
        registeredCommand, commandService
    );
  }
}

