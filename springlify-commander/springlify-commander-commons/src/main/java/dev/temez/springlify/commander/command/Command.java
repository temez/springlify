package dev.temez.springlify.commander.command;

import dev.temez.springlify.commander.command.metadata.CommandInvocationMetadata;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Unmodifiable;

import java.util.List;

public interface Command {

  @NotNull String getName();

  @NotNull String getDescription();

  @NotNull @Unmodifiable List<String> getAlias();

  @NotNull CommandType getType();

  @NotNull @Unmodifiable List<Command> getSubcommands();

  @NotNull CommandInvocationMetadata getCommandInvocationMetadata();
}
