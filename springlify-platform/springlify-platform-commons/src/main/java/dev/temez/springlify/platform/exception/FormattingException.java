package dev.temez.springlify.platform.exception;

import org.jetbrains.annotations.NotNull;

public class FormattingException extends RuntimeException {

  public FormattingException(@NotNull String message) {
    super(message);
  }
}
