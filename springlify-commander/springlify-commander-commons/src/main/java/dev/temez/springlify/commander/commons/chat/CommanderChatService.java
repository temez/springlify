package dev.temez.springlify.commander.commons.chat;

import dev.temez.springlify.commander.commons.sender.Sender;
import net.kyori.adventure.text.Component;
import org.jetbrains.annotations.NotNull;

/**
 * An interface representing a service for handling command-related chat messages.
 *
 * @since 0.5.8.9dev
 */
public interface CommanderChatService {

  /**
   * Sends an error message to the specified sender with the given key and replacers.
   *
   * @param sender    The sender to whom the error message will be sent.
   * @param key       The key identifying the error message.
   * @param replacers The replacers to be used in the error message.
   */
  void sendErrorMessage(
      @NotNull Sender<?> sender,
      @NotNull String key,
      Object @NotNull ... replacers
  );


  /**
   * Sends a message to the specified sender with the given key and replacers.
   *
   * @param sender    The sender to whom the error message will be sent.
   * @param component The message component.
   */
  void sendMessage(
      @NotNull Sender<?> sender,
      @NotNull Component component
  );
}
