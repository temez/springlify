package dev.temez.springlify.commander.exception.argument;

import dev.temez.springlify.commander.exception.CommanderException;
import org.jetbrains.annotations.NotNull;

public class ArgumentException extends CommanderException {

  public ArgumentException(@NotNull String message) {
    super(message);
  }
}
