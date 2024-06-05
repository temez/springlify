package dev.temez.springlify.commander.command.invocation;

import dev.temez.springlify.commander.command.Command;
import dev.temez.springlify.commander.command.sender.Sender;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldDefaults;
import org.jetbrains.annotations.NotNull;

import java.util.List;

@Getter
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public final class CommandInvocationImpl implements CommandInvocation {

  @NotNull
  final Sender<?> sender;

  @NotNull
  @Setter
  Command command;

  @NotNull
  @Setter
  List<String> arguments;

  @Override
  public int getLastArgumentIndex() {
    return arguments.isEmpty() ? 0 : arguments.size() - 1;
  }

  @Override
  public @NotNull String getLastArgument() throws IndexOutOfBoundsException {
    return arguments.get(getLastArgumentIndex());
  }
}
