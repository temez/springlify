package dev.temez.springlify.commander.command;

import dev.temez.springlify.commander.command.metadata.CommandInvocationMetadata;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.jetbrains.annotations.NotNull;

import java.util.List;

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
}
