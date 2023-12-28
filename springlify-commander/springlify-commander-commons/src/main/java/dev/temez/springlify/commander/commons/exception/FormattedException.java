package dev.temez.springlify.commander.commons.exception;

import org.jetbrains.annotations.NotNull;

public class FormattedException extends RuntimeException {

  public FormattedException(@NotNull String format, Object @NotNull ... replacers) {
    super(String.format(format, replacers));
  }
}
