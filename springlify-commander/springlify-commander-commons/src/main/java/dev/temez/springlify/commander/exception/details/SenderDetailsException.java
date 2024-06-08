package dev.temez.springlify.commander.exception.details;

import dev.temez.springlify.commander.exception.CommandException;
import org.jetbrains.annotations.NotNull;

public class SenderDetailsException extends CommandException {

  public SenderDetailsException(@NotNull String message) {
    super(message);
  }
}
