package dev.temez.springlify.test.service;

import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import net.kyori.adventure.text.Component;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 * The {@code SimpleService} class is a Spring service that implements the Bukkit {@code Listener}
 * interface. It listens for the {@code PlayerJoinEvent} and sends a profile-specific message
 * to the joined player.
 *
 * <p>The value of {@code someProfileSpecificValue} is injected from the Spring environment
 * using the @code @Value} annotation, based on the specified profile configuration.</p>
 *
 * @since 0.0.3.2
 */
@Service
@FieldDefaults(level = AccessLevel.PRIVATE)
public final class SimpleService implements Listener {

  @Value("${some.profile.value}")
  String someProfileSpecificValue;

  @EventHandler
  private void onEvent(@NotNull PlayerJoinEvent event) {
    event.getPlayer().sendMessage(Component.text(someProfileSpecificValue));
  }
}
