package dev.temez.springlify.commander.commons.exception;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.FieldDefaults;
import org.jetbrains.annotations.NotNull;


@Getter
@FieldDefaults(makeFinal = true, level = AccessLevel.PROTECTED)
public class ConformableException extends RuntimeException {

  Object @NotNull [] replacers;

  public ConformableException(@NotNull String message, Object @NotNull ... replacers) {
    super(message);
    this.replacers = replacers;
  }
}
