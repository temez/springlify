package dev.temez.springlify.commander.commons.execution.preprocessor.impl;

import dev.temez.springlify.commander.commons.command.RegisteredCommand;
import dev.temez.springlify.commander.commons.exception.ConformableException;
import dev.temez.springlify.commander.commons.execution.CommandExecution;
import dev.temez.springlify.commander.commons.execution.preprocessor.ExecutionPreprocessor;
import java.util.Comparator;
import java.util.List;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.springframework.stereotype.Component;

/**
 * Implementation of the ExecutionPreprocessor interface for handling subcommands.
 *
 * @since 0.5.8.9dev
 */
@Component
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public final class SubcommandPreprocessor implements ExecutionPreprocessor {

  /**
   * Gets the subcommand that matches the given command execution.
   *
   * @param execution The command execution context.
   * @return The matched subcommand, or null if none is found.
   */
  private @Nullable RegisteredCommand getSubCommand(@NotNull CommandExecution execution) {
    List<String> args = execution.getArguments();
    String argumentsString = String.join(" ", args);

    RegisteredCommand command = execution.getCommand();
    return command.getSubcommands()
        .stream()
        .sorted(Comparator.comparingInt(a -> a.getName().length()))
        .filter(subcommand -> argumentsString.startsWith(subcommand.getName()))
        .findFirst()
        .orElse(null);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void process(@NotNull CommandExecution execution) throws ConformableException {
    RegisteredCommand subCommand = getSubCommand(execution);
    if (subCommand != null) {
      execution.setCommand(subCommand);
      execution.setArguments(
          execution.getArguments()
              .stream()
              .skip(1)
              .toList()
      );
    }
  }
}
