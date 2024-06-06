package dev.temez.springlify.commander.command.preprocessor;

import dev.temez.springlify.commander.command.invocation.CommandInvocation;
import org.jetbrains.annotations.NotNull;

public interface InvocationPreprocessor {

  void process(@NotNull CommandInvocation invocation);
}
