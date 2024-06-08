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

/**
 * Implementation of {@link CommandInvocation}.
 *
 * @since 0.7.0.0-RC1
 */
@Getter
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CommandInvocationImpl implements CommandInvocation {

  @NotNull
  final Sender<?> sender;

  @NotNull
  @Setter
  Command command;

  @NotNull
  @Setter
  List<String> arguments;

  /**
   * Retrieves the index of the last argument.
   *
   * @return The index of the last argument.
   */
  @Override
  public int getLastArgumentIndex() {
    return arguments.isEmpty() ? 0 : arguments.size() - 1;
  }

  /**
   * Retrieves the last argument.
   *
   * @return The last argument.
   * @throws IndexOutOfBoundsException If there are no arguments.
   */
  @Override
  public @NotNull String getLastArgument() throws IndexOutOfBoundsException {
    return arguments.get(getLastArgumentIndex());
  }
}
