package dev.temez.springlify.commander.command.preprocessor;

import dev.temez.springlify.commander.command.invocation.CommandInvocation;
import dev.temez.springlify.commander.exception.CommandException;
import org.jetbrains.annotations.NotNull;

public interface ExecutionPreprocessor {

  void process(@NotNull CommandInvocation execution) throws CommandException;
}
