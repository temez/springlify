package dev.temez.springlify.platform.server;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.PluginCommand;
import org.bukkit.command.TabCompleter;
import org.bukkit.event.HandlerList;
import org.bukkit.event.Listener;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;

/**
 * Implementation of {@link ServerPlatformAdapter} for the Bukkit server platform.
 *
 * <p>This class registers and unregisters event listeners with the Bukkit {@link PluginManager}.
 * The class uses Lombok annotations to generate constructors and manage field defaults.</p>
 *
 * @see ServerPlatformAdapter
 * @see Listener
 * @see PluginManager
 * @see HandlerList
 * @see JavaPlugin
 * @since 0.7.0.0-RC1
 */
@Slf4j
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public final class BukkitServerPlatformAdapter implements ServerPlatformAdapter {

  @NotNull
  JavaPlugin plugin;

  @NotNull
  PluginManager pluginManager;

  /**
   * {@inheritDoc}
   *
   * @param listener the event listener to be registered
   */
  @Override
  public void registerEventListener(@NotNull Object listener) {
    pluginManager.registerEvents((Listener) listener, plugin);
    log.debug("Registered {} as an event listener.", listener.getClass().getSimpleName());
  }

  /**
   * {@inheritDoc}
   *
   * @param listener the event listener to be unregistered
   */
  @Override
  public void unregisterEventListener(@NotNull Object listener) {
    HandlerList.unregisterAll((Listener) listener);
    log.debug("Unregistered event listener: {}", listener.getClass().getSimpleName());
  }

  /**
   * {@inheritDoc}
   *
   * @param command         The command to register.
   * @param commandExecutor The command executor.
   * @param alias           The command aliases.
   */
  @Override
  public void registerCommandExecutor(@NotNull String command, @NotNull Object commandExecutor, @NotNull String... alias) {
    CommandExecutor executor = (CommandExecutor) commandExecutor;
    PluginCommand pluginCommand = plugin.getCommand(command);
    if (pluginCommand == null) {
      log.warn("It seems, you forgot to add the command '{}' to plugin.yml", command);
      return;
    }
    pluginCommand.setExecutor(executor);
    log.debug("Registered {} as a command executor.", commandExecutor.getClass().getSimpleName());
    if (executor instanceof TabCompleter tabCompleter) {
      pluginCommand.setTabCompleter(tabCompleter);
      log.debug("Registered {} as a tab completer.", commandExecutor.getClass().getSimpleName());
    }
  }
}
