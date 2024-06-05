package dev.temez.springlify.commander.command.filter.simple.impl;

import dev.temez.springlify.commander.annotation.context.Global;
import dev.temez.springlify.commander.command.CommandType;
import dev.temez.springlify.commander.command.Command;
import dev.temez.springlify.commander.command.filter.simple.SimpleCommandFilter;
import dev.temez.springlify.commander.command.sender.Sender;
import dev.temez.springlify.commander.exception.CommandFilterException;
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Component;

@Global
@Component
@RequiredArgsConstructor
public final class CommandSourceFilter implements SimpleCommandFilter {

  @Override
  public void filter(@NotNull Command command, @NotNull Sender<?> sender) throws CommandFilterException {
    if (sender.isConsoleSender() && command.getType() == CommandType.INGAME) {
      throw new CommandFilterException("commander.filter.command-source.available-only-from-game");
    }
    if (!sender.isConsoleSender() && command.getType() == CommandType.CONSOLE) {
      throw new CommandFilterException("commander.filter.command-source.available-only-from-console");
    }
  }
}
