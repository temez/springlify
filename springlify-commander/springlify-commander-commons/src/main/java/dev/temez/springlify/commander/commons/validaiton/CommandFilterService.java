package dev.temez.springlify.commander.commons.validaiton;

import dev.temez.springlify.commander.commons.command.RegisteredCommand;
import dev.temez.springlify.commander.commons.exception.ValidationException;
import dev.temez.springlify.commander.commons.sender.Sender;
import org.jetbrains.annotations.NotNull;

public interface CommandFilterService {

  boolean isAccessible(@NotNull Sender<?> sender, @NotNull RegisteredCommand command);

  void validate(
      @NotNull RegisteredCommand command,
      @NotNull Sender<?> sender
  ) throws ValidationException;
}
