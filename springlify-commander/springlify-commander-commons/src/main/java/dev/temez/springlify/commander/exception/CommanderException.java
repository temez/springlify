package dev.temez.springlify.commander.exception;

import org.jetbrains.annotations.NotNull;

public class CommanderException extends RuntimeException {

  public CommanderException(@NotNull String message) {
    super(message);
  }
}
