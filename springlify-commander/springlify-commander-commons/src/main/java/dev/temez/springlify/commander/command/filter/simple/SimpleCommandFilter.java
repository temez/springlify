package dev.temez.springlify.commander.command.filter.simple;

import dev.temez.springlify.commander.command.Command;
import dev.temez.springlify.commander.command.sender.Sender;
import dev.temez.springlify.commander.exception.CommandFilterException;
import org.jetbrains.annotations.NotNull;

public interface SimpleCommandFilter {

  void filter(@NotNull Command command, @NotNull Sender<?> sender) throws CommandFilterException;
}