package dev.temez.springlify.commander.exception.argument;

import org.jetbrains.annotations.NotNull;

public class ArgumentAdapterNotFoundException extends ArgumentAdapterException {

  public ArgumentAdapterNotFoundException(@NotNull String message) {
    super(message);
  }
}
