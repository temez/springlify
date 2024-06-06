package dev.temez.springlify.commander.command.filter.simple;

import dev.temez.springlify.commander.command.Command;
import dev.temez.springlify.commander.command.sender.Sender;
import dev.temez.springlify.commander.exception.filter.CommandFilterException;
import org.jetbrains.annotations.NotNull;

public interface SimpleCommandFilter {

  void doFilter(@NotNull Sender<?> sender, @NotNull Command command) throws CommandFilterException;
}
