package dev.temez.springlify.commander.commons.help;

import dev.temez.springlify.commander.commons.command.RegisteredCommand;
import dev.temez.springlify.commander.commons.sender.Sender;
import org.jetbrains.annotations.NotNull;

/**
 * Service interface for sending help messages related to commands.
 *
 * @since 0.5.8.9dev
 */
public interface CommandHelpService {

  /**
   * Sends a help message for the specified command to the given sender.
   *
   * @param sender  The sender to receive the help message.
   * @param command The command for which the help message is sent.
   */
  void sendHelpMessage(@NotNull Sender<?> sender, @NotNull RegisteredCommand command);
}
