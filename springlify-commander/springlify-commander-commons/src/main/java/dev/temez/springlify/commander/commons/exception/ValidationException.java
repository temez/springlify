package dev.temez.springlify.commander.commons.exception;

import org.jetbrains.annotations.NotNull;

/**
 * Exception class for validation-specific exceptions.
 *
 * @since 0.5.8.9dev
 */
public class ValidationException extends ConformableException {

  /**
   * Constructs a new ValidationException with the specified message and replacers.
   *
   * @param message   The detail message.
   * @param replacers Objects to be used as replacements in the message.
   */
  public ValidationException(@NotNull String message, Object @NotNull ... replacers) {
    super(message, replacers);
  }
}
