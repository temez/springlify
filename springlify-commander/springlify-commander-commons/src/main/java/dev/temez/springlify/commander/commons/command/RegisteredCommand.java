package dev.temez.springlify.commander.commons.command;

import dev.temez.springlify.commander.commons.annotation.Command;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.jetbrains.annotations.Unmodifiable;

@Getter
@Builder
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public final class RegisteredCommand {

  @Nullable RegisteredCommand parentCommand;

  @NotNull String name;

  @NotNull String description;

  @Builder.Default
  @NotNull @Unmodifiable List<String> alias = Collections.emptyList();

  @NotNull Command.CommandType type;

  @Builder.Default
  @NotNull List<RegisteredCommand> subcommands = new ArrayList<>();

  @NotNull CommandExecutionContext executionContext;

  @NotNull CommandValidationContext validationContext;

  public @NotNull RegisteredCommand getRootCommand() {
    RegisteredCommand command = this;
    while (command.getParentCommand() != null) {
      command = command.getParentCommand();
    }
    return command;
  }

  public @NotNull String getFullName() {
    List<RegisteredCommand> commandChain = new ArrayList<>();
    RegisteredCommand command = this;
    commandChain.add(command);
    while (command.getParentCommand() != null) {
      commandChain.add(command.getParentCommand());
      command = command.getParentCommand();
    }
    Collections.reverse(commandChain);
    return String.join(
        " ",
        commandChain.stream().map(RegisteredCommand::getName).toList()
    );
  }

}
