package dev.temez.springlify.commander.command.filter;

import dev.temez.springlify.commander.command.Command;
import dev.temez.springlify.commander.command.sender.Sender;
import dev.temez.springlify.commander.exception.filter.CommandFilterException;
import org.jetbrains.annotations.NotNull;

public interface CommandFilterService {

  void filter(@NotNull Sender<?> sender, @NotNull Command command) throws CommandFilterException;

  boolean isAccessible(@NotNull Sender<?> sender, @NotNull Command command);
}
