package dev.temez.springlify.commander.exception;

import org.jetbrains.annotations.NotNull;

public class CommandExecutionException extends CommandException {

  public CommandExecutionException(@NotNull String message) {
    super(message);
  }
}
