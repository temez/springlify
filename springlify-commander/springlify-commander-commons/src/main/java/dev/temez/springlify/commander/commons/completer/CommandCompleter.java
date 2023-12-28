package dev.temez.springlify.commander.commons.completer;

import dev.temez.springlify.commander.commons.execution.CommandExecution;
import java.util.List;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Unmodifiable;

public interface CommandCompleter {

  @NotNull @Unmodifiable List<String> complete(
      @NotNull CommandExecution commandExecution
  );

  @NotNull @Unmodifiable List<String> completeSorted(
      @NotNull CommandExecution commandExecution
  );
}
