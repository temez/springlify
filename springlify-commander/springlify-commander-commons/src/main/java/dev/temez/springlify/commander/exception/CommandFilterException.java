package dev.temez.springlify.commander.exception;

import org.jetbrains.annotations.NotNull;

public class CommandFilterException extends CommandException {

  public CommandFilterException(@NotNull String message) {
    super(message);
  }
}
