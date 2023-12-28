package dev.temez.springlify.platform.bukkit.service.chat;

import io.papermc.paper.chat.ChatRenderer;
import io.papermc.paper.event.player.AsyncChatEvent;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.jetbrains.annotations.NotNull;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.stereotype.Service;

/**
 * This class is a Spring service component that handles player messaging events.
 * It listens for the {@link AsyncChatEvent} and uses a provided {@link ChatRenderer}
 * to render chat messages.
 *
 * @see AsyncChatEvent
 * @see ChatRenderer
 * @since 0.5.9.8dev
 */
@Service
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
@RequiredArgsConstructor
@ConditionalOnBean(ChatRenderer.class)
public class PlayerChatService implements Listener {

  /**
   * The `ChatRenderer` used for rendering chat messages.
   */
  @NotNull ChatRenderer renderer;

  /**
   * This method listens for the {@link AsyncChatEvent} and sets the event's renderer to the
   * provided {@link ChatRenderer}.
   *
   * @param event The {@link AsyncChatEvent} being handled.
   */
  @EventHandler(ignoreCancelled = true, priority = EventPriority.LOWEST)
  private void onChat(@NotNull AsyncChatEvent event) {
    event.renderer(renderer);
  }
}
