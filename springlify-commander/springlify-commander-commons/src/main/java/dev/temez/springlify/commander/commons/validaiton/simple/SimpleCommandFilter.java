package dev.temez.springlify.commander.commons.validaiton.simple;

import dev.temez.springlify.commander.commons.command.RegisteredCommand;
import dev.temez.springlify.commander.commons.exception.ConformableException;
import dev.temez.springlify.commander.commons.sender.Sender;
import org.jetbrains.annotations.NotNull;

public interface SimpleCommandFilter {

  void filter(
      @NotNull RegisteredCommand command,
      @NotNull Sender<?> sender
  ) throws ConformableException;
}
