package dev.temez.springlify.commander.exception;

import org.jetbrains.annotations.NotNull;

public class CommandDiscoveryException extends CommandException {

  public CommandDiscoveryException(@NotNull String message) {
    super(message);
  }
}
