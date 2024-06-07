package dev.temez.springlify.platform.exception;

import org.jetbrains.annotations.NotNull;

/**
 * Exception thrown to indicate an error related to registry operations.
 *
 * <p>This exception is typically thrown when there is an issue with accessing or manipulating registry entries.</p>
 *
 * @since 0.7.0.0-RC1
 */
public class RegistryException extends RuntimeException {

  /**
   * Constructs a new registry exception with the specified detail message.
   *
   * @param message the detail message
   */
  public RegistryException(@NotNull String message) {
    super(message);
  }
}
