package dev.temez.springlify.commander.command.completer;

import dev.temez.springlify.commander.command.invocation.CommandInvocation;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Unmodifiable;

import java.util.List;

/**
 * Interface for command completers that provide completion suggestions for commands.
 * <p>
 * Implementations of this interface are responsible for generating completion suggestions
 * based on the current state of the command invocation.
 * </p>
 *
 * @since 0.7.0.0-RC1
 */
public interface CommandCompleter {

  /**
   * Provides a list of completion suggestions for the given command invocation.
   *
   * @param commandInvocation The current state of the command invocation.
   * @return An unmodifiable list of completion suggestions.
   */
  @NotNull @Unmodifiable List<String> complete(@NotNull CommandInvocation commandInvocation);

  /**
   * Provides a sorted list of completion suggestions for the given command invocation.
   *
   * @param commandInvocation The current state of the command invocation.
   * @return An unmodifiable and sorted list of completion suggestions.
   */
  @NotNull @Unmodifiable List<String> completeSorted(@NotNull CommandInvocation commandInvocation);
}
