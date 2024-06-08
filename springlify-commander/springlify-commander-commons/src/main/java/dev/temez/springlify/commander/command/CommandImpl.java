package dev.temez.springlify.commander.command;

import dev.temez.springlify.commander.command.metadata.CommandInvocationMetadata;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldDefaults;
import lombok.experimental.NonFinal;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Implementation of {@link Command}.
 *
 * @since 0.7.0.0-RC1
 */
@Getter
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class CommandImpl implements Command {

  @NotNull
  String name;

  @NotNull
  String description;

  @NotNull
  List<String> alias;

  @NotNull
  CommandType type;

  @NotNull
  List<Command> subcommands;

  @NotNull
  CommandInvocationMetadata commandInvocationMetadata;

  @Setter
  @Nullable
  @NonFinal
  Command parentCommand;

  /**
   * Retrieves the root command of the command hierarchy.
   *
   * @return The root command of the hierarchy.
   */
  @Override
  public @NotNull Command getRootCommand() {
    Command command = this;
    while (command.getParentCommand() != null) {
      command = command.getParentCommand();
    }
    return command;
  }

  /**
   * Retrieves the full name of the command, including its parent commands (if any).
   *
   * @return The full name of the command.
   */
  @Override
  public @NotNull String getFullName() {
    List<Command> commandChain = new ArrayList<>();
    Command command = this;
    commandChain.add(command);
    while (command.getParentCommand() != null) {
      commandChain.add(command.getParentCommand());
      command = command.getParentCommand();
    }
    Collections.reverse(commandChain);
    return commandChain.stream().map(Command::getName).collect(Collectors.joining(" "));
  }
}
