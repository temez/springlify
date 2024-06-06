package dev.temez.springlify.commander.service;

import dev.temez.springlify.commander.command.invocation.CommandInvocation;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Unmodifiable;

import java.util.List;

public interface CommandService {

  @NotNull @Unmodifiable List<String> complete(@NotNull CommandInvocation commandInvocation);

  void execute(@NotNull CommandInvocation commandInvocation);
}
