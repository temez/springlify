package dev.temez.springlify.commander.command.platform;

import dev.temez.springlify.commander.command.Command;
import dev.temez.springlify.commander.command.filter.CommandFilterService;
import dev.temez.springlify.commander.service.CommandService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Component;

/**
 * A factory class for creating instances of {@link CommanderBukkitCommand}.
 * This class implements the {@link PlatformCommandFactory} interface for {@link CommanderBukkitCommand}.
 * It uses the provided {@link CommandService} and {@link CommandFilterService} to create the command instances.
 *
 * @since 0.7.0.0-RC1
 */
@Component
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class BukkitCommandFactory implements PlatformCommandFactory<CommanderBukkitCommand> {

  @NotNull
  CommandService commandService;

  @NotNull
  CommandFilterService commandFilterService;

  /**
   * Creates a new instance of {@link CommanderBukkitCommand} for the given registered command.
   *
   * @param registeredCommand the command to create a {@link CommanderBukkitCommand} for
   * @return a new {@link CommanderBukkitCommand} instance
   */
  @Override
  public @NotNull CommanderBukkitCommand create(@NotNull Command registeredCommand) {
    return new CommanderBukkitCommand(registeredCommand, commandService, commandFilterService);
  }
}


