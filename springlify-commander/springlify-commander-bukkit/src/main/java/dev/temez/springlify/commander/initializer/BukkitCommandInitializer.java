package dev.temez.springlify.commander.initializer;

import dev.temez.springlify.commander.command.discoverer.CommandDiscoverer;
import dev.temez.springlify.commander.command.platform.CommanderBukkitCommand;
import dev.temez.springlify.commander.command.platform.PlatformCommandFactory;
import dev.temez.springlify.commander.command.platform.PlatformCommandRegistrar;
import org.jetbrains.annotations.NotNull;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

/**
 * Initializes and registers Bukkit-specific commands.
 * Extends {@link AbstractCommandInitializer} with {@link CommanderBukkitCommand} as the platform-specific command type.
 *
 * @since 0.7.0.0-RC1
 */
@Component
public final class BukkitCommandInitializer extends AbstractCommandInitializer<CommanderBukkitCommand> {

  /**
   * Constructs a {@code BukkitCommandInitializer} with the specified dependencies.
   *
   * @param commandFactory    the factory for creating Bukkit-specific commands
   * @param commandRegistrar  the registrar for registering Bukkit-specific commands
   * @param commandDiscoverer the discoverer for discovering commands
   */
  @Lazy
  public BukkitCommandInitializer(
      @NotNull PlatformCommandFactory<CommanderBukkitCommand> commandFactory,
      @NotNull PlatformCommandRegistrar<CommanderBukkitCommand> commandRegistrar,
      @NotNull CommandDiscoverer commandDiscoverer
  ) {
    super(commandFactory, commandRegistrar, commandDiscoverer);
  }
}
