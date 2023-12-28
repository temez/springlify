package dev.temez.springlify.commander.commons.execution.preprocessor;

import dev.temez.springlify.commander.commons.exception.ConformableException;
import dev.temez.springlify.commander.commons.execution.CommandExecution;
import org.jetbrains.annotations.NotNull;

public interface ExecutionPreprocessor {

  void process(@NotNull CommandExecution execution) throws ConformableException;
}
