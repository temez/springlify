package dev.temez.springlify.commander.command.invocation;

import dev.temez.springlify.commander.command.Command;
import org.jetbrains.annotations.NotNull;

public interface CommandInvocationFactory {

  @NotNull
  CommandInvocation create(@NotNull Command registeredCommand, Object @NotNull ... objects);
}
