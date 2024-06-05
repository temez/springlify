package dev.temez.springlify.platform.exception;

import org.jetbrains.annotations.NotNull;

public class RegistryException extends RuntimeException {

  public RegistryException(@NotNull String message) {
    super(message);
  }
}
