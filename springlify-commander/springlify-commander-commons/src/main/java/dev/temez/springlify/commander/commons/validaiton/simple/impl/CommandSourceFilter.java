package dev.temez.springlify.commander.commons.validaiton.simple.impl;

import dev.temez.springlify.commander.commons.annotation.Command;
import dev.temez.springlify.commander.commons.annotation.context.Global;
import dev.temez.springlify.commander.commons.command.RegisteredCommand;
import dev.temez.springlify.commander.commons.exception.ValidationException;
import dev.temez.springlify.commander.commons.sender.Sender;
import dev.temez.springlify.commander.commons.validaiton.simple.SimpleCommandFilter;
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Component;

/**
 * Global filter that restricts command execution based on the command source (console or in-game).
 *
 * <p>This filter is applied globally to all commands.</p>
 *
 * @since 0.5.8.9dev
 */
@Global
@Component
@RequiredArgsConstructor
public final class CommandSourceFilter implements SimpleCommandFilter {

  /**
   * {@inheritDoc}
   *
   * <p>Throws a {@link ValidationException} if the command source and type are incompatible.</p>
   *
   * @param command The registered command being executed.
   * @param sender  The sender of the command.
   * @throws ValidationException If the command source and type are incompatible.
   */
  @Override
  public void filter(
      @NotNull RegisteredCommand command,
      @NotNull Sender<?> sender
  ) throws ValidationException {
    if (sender.isConsoleSender() && command.getType() == Command.CommandType.INGAME) {
      throw new ValidationException("commander.filter.command-source.available-only-from-game");
    }
    if (!sender.isConsoleSender() && command.getType() == Command.CommandType.CONSOLE) {
      throw new ValidationException("commander.filter.command-source.available-only-from-console");
    }
  }
}
