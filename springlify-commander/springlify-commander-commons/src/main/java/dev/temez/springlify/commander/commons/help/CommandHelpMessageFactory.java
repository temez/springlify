package dev.temez.springlify.commander.commons.help;

import dev.temez.springlify.commander.commons.command.RegisteredCommand;
import dev.temez.springlify.commander.commons.sender.Sender;
import java.util.List;
import org.jetbrains.annotations.NotNull;

/**
 * Interface for a factory that generates help messages for a given command and sender.
 *
 * @since 0.5.8.9dev
 */
public interface CommandHelpMessageFactory {

  /**
   * Gets a list of help messages for the specified command and sender.
   *
   * @param sender  The sender to receive the help messages.
   * @param command The command for which help messages are generated.
   * @return A list of help messages.
   */
  @NotNull List<String> getHelpMessage(
      @NotNull Sender<?> sender,
      @NotNull RegisteredCommand command
  );
}
