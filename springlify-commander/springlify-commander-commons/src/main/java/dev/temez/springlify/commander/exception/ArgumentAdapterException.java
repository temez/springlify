package dev.temez.springlify.commander.exception;

import org.jetbrains.annotations.NotNull;

public class ArgumentAdapterException extends ArgumentException {

  public ArgumentAdapterException(@NotNull String message) {
    super(message);
  }
}
