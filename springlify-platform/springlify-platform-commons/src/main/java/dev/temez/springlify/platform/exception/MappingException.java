package dev.temez.springlify.platform.exception;

import org.jetbrains.annotations.NotNull;

/**
 * Exception thrown to indicate an error related to mapping operations.
 *
 * <p>This exception is typically thrown when there is an issue with mapping between different data formats or structures.</p>
 *
 * @since 0.7.0.0-RC1
 */
public class MappingException extends RuntimeException {

  /**
   * Constructs a new mapping exception with the specified detail message.
   *
   * @param message the detail message
   */
  public MappingException(@NotNull String message) {
    super(message);
  }
}
