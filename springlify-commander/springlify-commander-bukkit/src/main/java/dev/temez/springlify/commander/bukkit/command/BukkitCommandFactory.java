package dev.temez.springlify.commander.bukkit.command;

import dev.temez.springlify.commander.commons.command.RegisteredCommand;
import dev.temez.springlify.commander.commons.command.platform.PlatformCommand;
import dev.temez.springlify.commander.commons.command.platform.PlatformCommandFactory;
import dev.temez.springlify.commander.commons.completer.CommandCompleter;
import dev.temez.springlify.commander.commons.exception.handler.CommanderExceptionHandler;
import dev.temez.springlify.commander.commons.executor.CommandExecutor;
import dev.temez.springlify.commander.commons.validaiton.CommandFilterService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Component;

/**
 * Implementation of {@link PlatformCommandFactory} for Bukkit platform.
 *
 * <p>This factory creates instances of {@link PlatformCommand} for registered commands.</p>
 *
 * @since 0.5.8.9dev
 */
@Component
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public final class BukkitCommandFactory implements PlatformCommandFactory {

  @NotNull CommandCompleter commandCompleter;

  @NotNull CommandFilterService commandFilterService;

  @NotNull CommandExecutor commandExecutor;

  @NotNull CommanderExceptionHandler exceptionHandler;

  /**
   * {@inheritDoc}
   */
  @Override
  public @NotNull PlatformCommand create(@NotNull RegisteredCommand registeredCommand) {

    return CommanderBukkitCommand.builder()
        .command(registeredCommand)
        .exceptionHandler(exceptionHandler)
        .commandCompleter(commandCompleter)
        .commandExecutor(commandExecutor)
        .commandFilterService(commandFilterService)
        .build();
  }
}

