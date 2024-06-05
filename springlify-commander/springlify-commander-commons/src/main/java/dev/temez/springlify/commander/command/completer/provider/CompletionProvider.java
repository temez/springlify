package dev.temez.springlify.commander.command.completer.provider;

import dev.temez.springlify.commander.command.invocation.CommandInvocation;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Unmodifiable;

import java.util.List;

public interface CompletionProvider {

  boolean supports(@NotNull CommandInvocation execution);

  @NotNull @Unmodifiable List<String> complete(@NotNull CommandInvocation execution);
}
