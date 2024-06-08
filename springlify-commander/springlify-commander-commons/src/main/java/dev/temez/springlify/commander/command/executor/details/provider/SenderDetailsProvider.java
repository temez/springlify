package dev.temez.springlify.commander.command.executor.details.provider;

import dev.temez.springlify.commander.command.Command;
import dev.temez.springlify.commander.command.sender.Sender;
import org.jetbrains.annotations.NotNull;

/**
 * A provider interface for resolving details about the sender of a command.
 * <p>
 * Implementations of this interface determine whether they support a specific command and provide a way to resolve sender details for that command.
 * </p>
 *
 * @since 0.7.0.0-RC1
 */
public interface SenderDetailsProvider {

  /**
   * Determines whether this provider supports the given command.
   *
   * @param command The command to check support for.
   * @return {@code true} if this provider supports the command, {@code false} otherwise.
   */
  boolean supports(@NotNull Command command);

  /**
   * Resolves details about the sender of the given command.
   *
   * @param command The command whose sender details are to be resolved.
   * @param sender  The sender of the command.
   * @return An object containing details about the sender.
   */
  @NotNull Object getSenderDetails(@NotNull Command command, @NotNull Sender<?> sender);

}
