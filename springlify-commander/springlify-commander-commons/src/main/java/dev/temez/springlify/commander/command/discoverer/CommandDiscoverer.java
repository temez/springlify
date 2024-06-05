package dev.temez.springlify.commander.command.discoverer;

import dev.temez.springlify.commander.command.Command;
import dev.temez.springlify.commander.exception.CommandDiscoveryException;
import org.jetbrains.annotations.NotNull;

public interface CommandDiscoverer<T> {

  @NotNull Command discover(@NotNull T executor) throws CommandDiscoveryException;
}
