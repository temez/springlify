package dev.temez.springlify.commander.commons.completer;

import dev.temez.springlify.commander.commons.execution.CommandExecution;
import java.util.List;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Unmodifiable;

/**
 * Interface for completing command-related tasks.
 *
 * @since 0.5.8.9dev
 */
public interface CommandCompleter {

  /**
   * Completes command-related tasks and returns a non-modifiable list of strings.
   *
   * @param commandExecution The command execution context.
   * @return A non-modifiable list of completed strings.
   */
  @NotNull
  List<String> complete(@NotNull CommandExecution commandExecution);

  /**
   * Completes command-related tasks, sorts the results, and returns a non-modifiable
   * list of strings.
   *
   * @param commandExecution The command execution context.
   * @return A non-modifiable list of completed and sorted strings.
   */
  @NotNull
  @Unmodifiable List<String> completeSorted(@NotNull CommandExecution commandExecution);
}
