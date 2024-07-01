package dev.temez.springlify.commander.command.filter.simple.impl;

import dev.temez.springlify.commander.annotation.context.Global;
import dev.temez.springlify.commander.command.Command;
import dev.temez.springlify.commander.command.CommandType;
import dev.temez.springlify.commander.command.filter.simple.SimpleCommandFilter;
import dev.temez.springlify.commander.command.sender.Sender;
import dev.temez.springlify.commander.exception.filter.CommandFilterException;
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Component;

/**
 * A global command filter that restricts command execution based on the command source.
 *
 * @since 0.7.0.0-RC1
 */
@Global
@Component
@RequiredArgsConstructor
public class CommandSourceFilter implements SimpleCommandFilter {

  /**
   * Performs filtering based on the command source.
   *
   * @param sender  The sender executing the command.
   * @param command The command being executed.
   * @throws CommandFilterException If the command source is not allowed for the given command.
   */
  @Override
  public void doFilter(@NotNull Sender<?> sender, @NotNull Command command) throws CommandFilterException {
    if (sender.isConsoleSender() && command.getType() == CommandType.INGAME) {
      throw new CommandFilterException("commander.filter.command-source.available-only-from-game");
    }
    if (!sender.isConsoleSender() && command.getType() == CommandType.CONSOLE) {
      throw new CommandFilterException("commander.filter.command-source.available-only-from-console");
    }
  }
}
