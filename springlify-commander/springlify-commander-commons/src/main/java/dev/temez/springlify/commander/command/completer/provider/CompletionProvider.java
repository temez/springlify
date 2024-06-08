package dev.temez.springlify.commander.command.completer.provider;

import dev.temez.springlify.commander.command.invocation.CommandInvocation;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Unmodifiable;

import java.util.List;

/**
 * An interface defining a provider for command completion suggestions.
 * <p>
 * Implementations of this interface should provide logic to determine if they support a given
 * command invocation and to generate completion suggestions for it.
 * </p>
 *
 * @see CommandInvocation
 * @since 0.7.0.0-RC1
 */
public interface CompletionProvider {

  /**
   * Determines whether this provider supports the given command invocation.
   *
   * @param commandInvocation The current state of the command invocation.
   * @return {@code true} if this provider supports the given command invocation, {@code false} otherwise.
   */
  boolean supports(@NotNull CommandInvocation commandInvocation);

  /**
   * Generates a list of completion suggestions for the given command invocation.
   *
   * @param commandInvocation The current state of the command invocation.
   * @return An unmodifiable list of completion suggestions.
   */
  @NotNull @Unmodifiable List<String> complete(@NotNull CommandInvocation commandInvocation);
}
