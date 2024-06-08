package dev.temez.springlify.starter.server;

import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.bukkit.event.HandlerList;
import org.bukkit.event.Listener;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.mockito.MockedStatic;
import org.mockito.Mockito;

import static org.mockito.Mockito.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
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
    try (MockedStatic<HandlerList> handlerList = Mockito.mockStatic(HandlerList.class)) {
      Listener listener = mock(Listener.class);

      serverPlatformAdapter.unregisterEventListener(listener);

      handlerList.verify(() -> HandlerList.unregisterAll(listener), times(1));
    }
  }
}