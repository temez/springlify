package dev.temez.springlify.commander.exception.filter;

import org.jetbrains.annotations.NotNull;

public class CommandFilterNotFoundException extends CommandFilterException {

  public CommandFilterNotFoundException(@NotNull String message) {
    super(message);
  }
}
