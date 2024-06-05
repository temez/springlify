package dev.temez.springlify.platform.exception;

import org.jetbrains.annotations.NotNull;

public class MappingException extends RuntimeException {

  public MappingException(@NotNull String message) {
    super(message);
  }
}
