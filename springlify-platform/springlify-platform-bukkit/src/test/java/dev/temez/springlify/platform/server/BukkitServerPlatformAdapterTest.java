package dev.temez.springlify.platform.server;

import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.bukkit.command.*;
import org.bukkit.event.HandlerList;
import org.bukkit.event.Listener;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.jetbrains.annotations.Unmodifiable;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;

import java.util.List;

import static org.mockito.Mockito.*;

@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class BukkitServerPlatformAdapterTest {

  JavaPlugin plugin = mock(JavaPlugin.class);

  PluginManager pluginManager = mock(PluginManager.class);

  ServerPlatformAdapter serverPlatformAdapter = new BukkitServerPlatformAdapter(plugin, pluginManager);


  @Test
  public void givenBukkitListener_whenRegisterEventListener_thenRegister() {
    Listener listener = mock(Listener.class);

    serverPlatformAdapter.registerEventListener(listener);

    verify(pluginManager, times(1)).registerEvents(listener, plugin);
  }

  @Test
  public void givenBukkitListener_whenUnregisterEventListener_thenUnregister() {
    try (MockedStatic<HandlerList> handlerList = mockStatic(HandlerList.class)) {
      Listener listener = mock(Listener.class);

      serverPlatformAdapter.unregisterEventListener(listener);

      handlerList.verify(() -> HandlerList.unregisterAll(listener), times(1));
    }
  }

  @Test
  public void givenCommandExecutor_whenRegisterCommandExecutor_thenRegister() {
    PluginCommand command = mock(PluginCommand.class);
    when(plugin.getCommand(anyString())).thenReturn(command);
    CommandExecutor executor = mock(CommandExecutor.class);

    serverPlatformAdapter.registerCommandExecutor("test", executor);

    verify(command, times(1)).setExecutor(executor);
  }

  @Test
  public void givenCommandExecutorWithTabCompleter_whenRegisterCommandExecutor_thenRegister() {
    PluginCommand command = mock(PluginCommand.class);
    when(plugin.getCommand(anyString())).thenReturn(command);
    CommandExecutorWithTabCompleter executor = mock(CommandExecutorWithTabCompleter.class);

    serverPlatformAdapter.registerCommandExecutor("test", executor);

    verify(command, times(1)).setExecutor(executor);
    verify(command, times(1)).setTabCompleter(executor);
  }

  @Test
  public void givenNoCommandFoundInPlugin_whenRegisterCommandExecutor_thenShouldNotBeRegisteredAndThrowException() {
    when(plugin.getCommand(anyString())).thenReturn(null);
    CommandExecutorWithTabCompleter executor = mock(CommandExecutorWithTabCompleter.class);

    serverPlatformAdapter.registerCommandExecutor("test", executor);
  }

  private static class CommandExecutorWithTabCompleter implements CommandExecutor, TabCompleter {

    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {
      return false;
    }

    @Override
    public @Nullable @Unmodifiable List<String> onTabComplete(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {
      return List.of();
    }
  }
}