package dev.temez.springlify.platform.commons.exception;

import org.jetbrains.annotations.NotNull;

/**
 * Custom exception class that allows formatting the exception message with replacers.
 */
public class FormattedException extends RuntimeException {

  /**
   * Constructs a {@code FormattedException} with the specified format and replacers.
   *
   * @param format    the format string
   * @param replacers the objects to be formatted and substituted in the message
   */
  public FormattedException(@NotNull String format, Object @NotNull ... replacers) {
    super(String.format(format, replacers));
  }
}
