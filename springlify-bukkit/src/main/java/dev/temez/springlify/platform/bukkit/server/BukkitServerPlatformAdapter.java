package dev.temez.springlify.platform.bukkit.server;

import dev.temez.springlify.commons.server.ServerPlatformAdapter;
import dev.temez.springlify.platform.bukkit.plugin.SpringlifyBukkitPlugin;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.log4j.Log4j2;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.PluginCommand;
import org.bukkit.command.TabCompleter;
import org.bukkit.event.HandlerList;
import org.bukkit.event.Listener;
import org.bukkit.plugin.Plugin;
import org.jetbrains.annotations.NotNull;


/**
 * This class implements {@link ServerPlatformAdapter} interface and implements
 * Bukkit server platform.
 */
@Log4j2
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PROTECTED)
public class BukkitServerPlatformAdapter implements ServerPlatformAdapter {

  @NotNull SpringlifyBukkitPlugin plugin;

  /**
   * Registers a listener with the Bukkit plugin manager.
   *
   * @param listener The listener to register.
   */
  @Override
  public void registerListener(@NotNull Object listener) {
    Bukkit.getPluginManager().registerEvents((Listener) listener, plugin);
    log.info("Registered {} as an event listener.", listener.getClass().getSimpleName());
  }

  /**
   * Unregisters a listener from the Bukkit plugin manager.
   *
   * @param listener The listener to unregister.
   */
  @Override
  public void unregisterListener(@NotNull Object listener) {
    HandlerList.unregisterAll((Listener) listener);
    log.info("Unregistered event listener: {}", listener.getClass().getSimpleName());
  }

  /**
   * Registers a command executor with the Bukkit plugin manager.
   *
   * @param command         The command to register.
   * @param commandExecutor The command executor.
   * @param alias           The command aliases.
   */
  @Override
  public void registerCommandExecutor(
      @NotNull String command,
      @NotNull Object commandExecutor,
      @NotNull String... alias
  ) {
    PluginCommand pluginCommand = plugin.getCommand(command);
    if (pluginCommand == null) {
      log.warn("It seems, you forgot to add the command '{}' to plugin.yml", command);
      return;
    }

    pluginCommand.setExecutor((CommandExecutor) commandExecutor);
    log.info(
        "Registered {} as a command executor.", commandExecutor.getClass().getSimpleName()
    );

    if (commandExecutor instanceof TabCompleter tabCompleter) {
      pluginCommand.setTabCompleter(tabCompleter);
      log.info(
          "Registered {} as a tab completer.", commandExecutor.getClass().getSimpleName()
      );
    }
  }
}
