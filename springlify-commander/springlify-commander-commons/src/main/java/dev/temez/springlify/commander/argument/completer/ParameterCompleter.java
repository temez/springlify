package dev.temez.springlify.commander.argument.completer;

import dev.temez.springlify.commander.command.sender.Sender;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Unmodifiable;

import java.util.List;

/**
 * An interface for providing completion suggestions for command parameters.
 * <p>
 * Implementations of this interface are responsible for generating a list of completion suggestions
 * based on the input provided by the command sender.
 * </p>
 *
 * @since 0.7.0.0-RC1
 */
public interface ParameterCompleter {

  /**
   * Generates a list of completion suggestions for the provided command sender.
   *
   * @param commandSender The command sender for whom completion suggestions are generated.
   * @return A list of completion suggestions.
   */
  @Unmodifiable
  @NotNull List<String> complete(@NotNull Sender<?> commandSender);

}
