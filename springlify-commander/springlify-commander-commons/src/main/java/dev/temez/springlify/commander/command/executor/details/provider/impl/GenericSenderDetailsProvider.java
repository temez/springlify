package dev.temez.springlify.commander.command.executor.details.provider.impl;

import dev.temez.springlify.commander.annotation.SenderDetailsSource;
import dev.temez.springlify.commander.command.Command;
import dev.temez.springlify.commander.command.executor.details.provider.SenderDetailsProvider;
import dev.temez.springlify.commander.command.sender.Sender;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Component;

/**
 * A generic implementation of the {@link SenderDetailsProvider} interface.
 * <p>
 * This provider supports all commands and resolves sender details by returning the platform-specific sender object.
 * </p>
 *
 * @see SenderDetailsProvider
 * @see Sender
 * @since 0.7.0.0-RC1
 */
@Component
public class GenericSenderDetailsProvider implements SenderDetailsProvider {

  /**
   * Determines whether this provider supports the given command.
   *
   * @param command The command to check support for.
   * @return {@code true} indicating that this provider supports all commands.
   */
  @Override
  public boolean supports(@NotNull Command command) {
    return !command.getCommandInvocationMetadata().getCommandMethod().isAnnotationPresent(SenderDetailsSource.class);
  }

  /**
   * Resolves details about the sender of the given command.
   *
   * @param command The command whose sender details are to be resolved.
   * @param sender  The sender of the command.
   * @return The platform-specific sender object.
   */
  @Override
  @NotNull
  public Object getSenderDetails(@NotNull Command command, @NotNull Sender<?> sender) {
    return sender.getPlatformSender();
  }
}
