package dev.temez.springlify.commander.exception.argument;

import org.jetbrains.annotations.NotNull;

public class ArgumentAdapterException extends ArgumentException {

  public ArgumentAdapterException(@NotNull String message) {
    super(message);
  }
}
