package dev.temez.springlify.commander.commons.completer;

import dev.temez.springlify.commander.commons.sender.Sender;
import java.util.List;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Unmodifiable;

/**
 * This interface represents an ArgumentCompleter, providing a method for completing
 * command arguments.
 *
 * @since 0.5.8.9dev
 */
public interface ArgumentCompleter {

  /**
   * Completes command arguments based on the provided command sender.
   *
   * @param commandSender The sender of the command.
   * @return A list of completed command arguments.
   */
  @Unmodifiable
  @NotNull List<String> complete(@NotNull Sender<?> commandSender);

}
