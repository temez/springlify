package dev.temez.springlify.commander.exception.filter;

import dev.temez.springlify.commander.exception.CommandException;
import org.jetbrains.annotations.NotNull;

public class CommandFilterException extends CommandException {

  public CommandFilterException(@NotNull String message) {
    super(message);
  }
}
