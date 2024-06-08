package dev.temez.springlify.commander.exception.discovery;

import dev.temez.springlify.commander.exception.CommandException;
import org.jetbrains.annotations.NotNull;

public class CommandDiscoveryException extends CommandException {

  public CommandDiscoveryException(@NotNull String message) {
    super(message);
  }
}
