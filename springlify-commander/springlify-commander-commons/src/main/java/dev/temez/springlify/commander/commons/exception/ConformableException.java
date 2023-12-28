package dev.temez.springlify.commander.commons.exception;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.FieldDefaults;
import org.jetbrains.annotations.NotNull;

/**
 * Exception class with support for message replacers.
 *
 * @since 0.5.8.9dev
 */
@Getter
@FieldDefaults(makeFinal = true, level = AccessLevel.PROTECTED)
public class ConformableException extends RuntimeException {

  Object @NotNull [] replacers;

  /**
   * Constructs a new ConformableException with the specified message and replacers.
   *
   * @param message   The detail message.
   * @param replacers Objects to be used as replacements in the message.
   */
  public ConformableException(@NotNull String message, Object @NotNull ... replacers) {
    super(message);
    this.replacers = replacers;
  }
}
