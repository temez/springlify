package dev.temez.springlify.commander.commons.adapter;

import dev.temez.springlify.commander.commons.completer.ArgumentCompleter;
import dev.temez.springlify.commander.commons.exception.ConformableException;
import dev.temez.springlify.commander.commons.sender.Sender;
import java.util.Collections;
import java.util.List;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Unmodifiable;

/**
 * This interface represents an ArgumentAdapter for a specific type T. It extends the
 * ArgumentCompleter interface, providing methods for completing and parsing command
 * arguments of the specified type.
 *
 * @param <T> The type of the argument that this adapter handles.
 * @since 0.5.9.8dev
 */
public interface ArgumentAdapter<T> extends ArgumentCompleter {

  /**
   * Default implementation returns an empty list, indicating that no completions are available for
   * this adapter.
   *
   * @param commandSender The sender of the command.
   * @return An empty list of completions.
   */
  @Override
  default @Unmodifiable @NotNull List<String> complete(@NotNull Sender<?> commandSender) {
    return Collections.emptyList();
  }

  /**
   * Gets the class object representing the target type T.
   *
   * @return The Class object for the target type.
   */
  @NotNull
  Class<T> getTargetClass();

  /**
   * Parses a raw argument into an instance of the target type T.
   *
   * @param commandSender The sender of the command.
   * @param rawArgument   The raw argument to be parsed.
   * @return An instance of the target type T.
   * @throws ConformableException If the parsing fails due to non-conformance.
   */
  @NotNull
  T parse(
      @NotNull Sender<?> commandSender,
      @NotNull String rawArgument
  ) throws ConformableException;
}
