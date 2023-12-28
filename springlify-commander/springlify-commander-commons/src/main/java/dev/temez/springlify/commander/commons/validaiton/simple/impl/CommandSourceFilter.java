package dev.temez.springlify.commander.commons.validaiton.simple.impl;

import dev.temez.springlify.commander.commons.annotation.Command;
import dev.temez.springlify.commander.commons.annotation.context.Global;
import dev.temez.springlify.commander.commons.command.RegisteredCommand;
import dev.temez.springlify.commander.commons.exception.ValidationException;
import dev.temez.springlify.commander.commons.sender.Sender;
import dev.temez.springlify.commander.commons.validaiton.simple.SimpleCommandFilter;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Component;

@Global
@Component
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public final class CommandSourceFilter implements SimpleCommandFilter {

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
