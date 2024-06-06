package dev.temez.springlify.commander.command.invocation;

import dev.temez.springlify.commander.command.Command;
import dev.temez.springlify.commander.command.sender.Sender;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Unmodifiable;

import java.util.List;

public interface CommandInvocation {

  @NotNull Sender<?> getSender();

  @NotNull Command getCommand();

  void setCommand(@NotNull Command command);

  @NotNull @Unmodifiable List<String> getArguments();

  void setArguments(@NotNull List<String> args);

  int getLastArgumentIndex();

  @NotNull String getLastArgument() throws IndexOutOfBoundsException;
}