package dev.temez.springlify.platform.velocity.service.chat;

import com.velocitypowered.api.command.CommandSource;
import org.jetbrains.annotations.NotNull;

/**
 * A service interface for sending chat messages to players in a Minecraft plugin.
 */
@SuppressWarnings("unused")
public interface ChatService {

  /**
   * Send a success message to a sender with optional text replacers.
   *
   * @param sender    The sender who will receive the success message.
   * @param message   The success message to send.
   * @param replacers Optional text replacers to apply to the message.
   */
  void sendMessage(
      @NotNull CommandSource sender,
      @NotNull String message,
      @NotNull Object... replacers
  );
}