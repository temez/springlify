package dev.temez.springlify.commander.exception;

import org.jetbrains.annotations.NotNull;

public class CommandException extends CommanderException {

  public CommandException(@NotNull String message) {
    super(message);
  }
}
