package dev.temez.springlify.starter.initializer;

import dev.temez.springlify.starter.server.ServerPlatformAdapter;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.bukkit.event.Listener;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.config.DestructionAwareBeanPostProcessor;

import static org.mockito.Mockito.*;

@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class EventHandlerInitializerTest {

  ServerPlatformAdapter serverPlatformAdapter = mock(ServerPlatformAdapter.class);

  DestructionAwareBeanPostProcessor eventHandlerInitializer = new EventHandlerInitializer(serverPlatformAdapter);

  @Test
  public void givenBukkitEventListener_whenPostProcessAfterInitialization_thenRegister() {
    Listener listener = mock(Listener.class);

    eventHandlerInitializer.postProcessAfterInitialization(listener, "listener");

    verify(serverPlatformAdapter, times(1)).registerEventListener(listener);
  }


  @Test
  public void givenBukkitEventListener_postProcessBeforeDestruction_thenUnregister() {
    Listener listener = mock(Listener.class);

    eventHandlerInitializer.postProcessBeforeDestruction(listener, "listener");

    verify(serverPlatformAdapter, times(1)).unregisterEventListener(listener);
  }

}