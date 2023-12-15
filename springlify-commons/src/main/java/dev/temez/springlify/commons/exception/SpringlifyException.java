package dev.temez.springlify.commons.exception;

import org.jetbrains.annotations.NotNull;

/**
 * The {@code SpringlifyException} class represents an exception that can be thrown when an issue
 * related to Springlify occurs. It extends the {@code RuntimeException} class, allowing it to be
 * used without the need for explicit exception handling in calling code.
 *
 * @since 0.5.9.3dev
 */
public class SpringlifyException extends RuntimeException {

  /**
   * Constructs a new {@code SpringlifyException} with the specified format string and arguments.
   *
   * @param message The format string for the exception message.
   * @param args    The arguments to be formatted into the exception message.
   */
  public SpringlifyException(String message, Object @NotNull ... args) {
    super(String.format(message, args));
  }
}
