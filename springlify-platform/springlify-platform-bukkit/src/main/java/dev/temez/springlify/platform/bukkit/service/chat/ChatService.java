package dev.temez.springlify.platform.bukkit.service.chat;

import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;

/**
 * The {@code ChatService} interface provides methods for sending messages to {@link CommandSender}.
 *
 * @since 0.5.9.8dev
 */
@SuppressWarnings("unused")
public interface ChatService {

  /**
   * Sends a formatted message to the specified {@link CommandSender}.
   *
   * @param sender    The command sender to receive the message.
   * @param message   The message to be sent.
   * @param replacers Objects used to replace placeholders in the message.
   */
  void sendMessage(@NotNull CommandSender sender,
                   @NotNull String message,
                   Object @NotNull ... replacers
  );

}
