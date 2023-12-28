package dev.temez.springlify.commander.commons.exception;

import org.jetbrains.annotations.NotNull;

public class ValidationException extends ConformableException {

  public ValidationException(@NotNull String message, Object @NotNull ... replacers) {
    super(message, replacers);
  }
}
