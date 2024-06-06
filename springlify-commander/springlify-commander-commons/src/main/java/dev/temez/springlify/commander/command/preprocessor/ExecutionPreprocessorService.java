package dev.temez.springlify.commander.command.preprocessor;

import dev.temez.springlify.commander.command.invocation.CommandInvocation;
import org.jetbrains.annotations.NotNull;

public interface ExecutionPreprocessorService {

  void process(@NotNull CommandInvocation commandInvocation);
}
