package dev.temez.springlify.commander.commons.validaiton;

import dev.temez.springlify.commander.commons.command.RegisteredCommand;
import dev.temez.springlify.commander.commons.exception.ValidationException;
import dev.temez.springlify.commander.commons.sender.Sender;
import org.jetbrains.annotations.NotNull;

/**
 * Service interface for checking the accessibility and validating commands.
 */
public interface CommandFilterService {

  /**
   * Checks if a command is accessible to the given sender.
   *
   * @param sender  The sender of the command.
   * @param command The command to check for accessibility.
   * @return {@code true} if the command is accessible; otherwise, {@code false}.
   */
  boolean isAccessible(@NotNull Sender<?> sender, @NotNull RegisteredCommand command);

  /**
   * Validates a command for the given sender.
   *
   * @param command The command to validate.
   * @param sender  The sender of the command.
   * @throws ValidationException If the validation fails due to specific criteria not being met.
   */
  void validate(
      @NotNull RegisteredCommand command,
      @NotNull Sender<?> sender
  ) throws ValidationException;
}
