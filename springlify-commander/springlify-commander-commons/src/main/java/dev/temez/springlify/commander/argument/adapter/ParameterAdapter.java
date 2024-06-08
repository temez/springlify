package dev.temez.springlify.commander.argument.adapter;

import dev.temez.springlify.commander.argument.completer.ParameterCompleter;
import dev.temez.springlify.commander.command.sender.Sender;
import dev.temez.springlify.commander.exception.argument.ArgumentException;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Unmodifiable;

import java.util.Collections;
import java.util.List;


/**
 * An interface for adapting raw command parameters into typed objects.
 * <p>
 * Implementations of this interface are responsible for parsing raw command parameters into typed objects of type {@code T}.
 * They may also provide completion suggestions for command parameters.
 * </p>
 *
 * @param <T> The type of the object to which the raw argument will be adapted.
 * @see ParameterCompleter
 * @since 0.7.0.0-RC1
 */
public interface ParameterAdapter<T> extends ParameterCompleter {

  /**
   * Parses the raw command parameter into a typed object of type {@code T}.
   *
   * @param commandSender The command sender from which the raw argument was received.
   * @param rawArgument   The raw command parameter to parse.
   * @return The parsed object of type {@code T}.
   * @throws ArgumentException If an error occurs during parsing.
   */
  @NotNull T parse(@NotNull Sender<?> commandSender, @NotNull String rawArgument) throws ArgumentException;

  /**
   * Generates a list of completion suggestions for the provided command sender.
   * <p>
   * This default implementation returns an empty list, indicating that no completion suggestions are provided.
   * </p>
   *
   * @param commandSender The command sender for whom completion suggestions are generated.
   * @return A list of completion suggestions.
   */
  @Override
  default @Unmodifiable @NotNull List<String> complete(@NotNull Sender<?> commandSender) {
    return Collections.emptyList();
  }
}
