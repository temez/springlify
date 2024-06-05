package dev.temez.springlify.commander.command.completer;

import dev.temez.springlify.commander.command.invocation.CommandInvocation;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Unmodifiable;

import java.util.List;

public interface CommandCompleter {

  @NotNull @Unmodifiable List<String> complete(@NotNull CommandInvocation commandInvocation);

  @NotNull @Unmodifiable List<String> completeSorted(@NotNull CommandInvocation commandInvocation);
}
