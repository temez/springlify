package dev.temez.springlify.commander.command.executor.details;

import dev.temez.springlify.commander.command.Command;
import dev.temez.springlify.commander.command.sender.Sender;
import dev.temez.springlify.commander.exception.details.SenderDetailsException;
import org.jetbrains.annotations.NotNull;

/**
 * Interface for resolving details about the sender of a command.
 *
 * @since 0.7.0.0-RC1
 */
public interface SenderDetailsResolver {

  /**
   * Resolves details about the sender of the given command.
   *
   * @param command The command whose sender details are to be resolved.
   * @param sender  The sender of the command.
   * @return The resolved sender details.
   * @throws SenderDetailsException If an error occurs while resolving sender details.
   */
  @NotNull
  Object resolve(@NotNull Command command, @NotNull Sender<?> sender) throws SenderDetailsException;
}
