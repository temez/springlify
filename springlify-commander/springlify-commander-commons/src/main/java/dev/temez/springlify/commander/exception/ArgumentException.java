package dev.temez.springlify.commander.exception;

import org.jetbrains.annotations.NotNull;

public class ArgumentException extends RuntimeException {

  public ArgumentException(@NotNull String message) {
    super(message);
  }
}
