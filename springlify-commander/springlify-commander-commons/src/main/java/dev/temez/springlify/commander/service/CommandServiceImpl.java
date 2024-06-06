package dev.temez.springlify.commander.service;

import dev.temez.springlify.commander.command.completer.CommandCompleter;
import dev.temez.springlify.commander.command.executor.CommandExecutor;
import dev.temez.springlify.commander.command.filter.CommandFilterService;
import dev.temez.springlify.commander.command.invocation.CommandInvocation;
import dev.temez.springlify.commander.command.preprocessor.ExecutionPreprocessorService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Unmodifiable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class CommandServiceImpl implements CommandService {

  @NotNull
  CommandCompleter commandCompleter;

  @NotNull
  CommandExecutor commandExecutor;

  @NotNull
  CommandFilterService commandFilterService;

  @NotNull
  ExecutionPreprocessorService executionPreprocessorService;

  @Override
  public @NotNull @Unmodifiable List<String> complete(@NotNull CommandInvocation commandInvocation) {
    executionPreprocessorService.process(commandInvocation);
    return commandCompleter.completeSorted(commandInvocation);
  }

  @Override
  public void execute(@NotNull CommandInvocation commandInvocation) {
    executionPreprocessorService.process(commandInvocation);
    commandFilterService.filter(commandInvocation.getSender(), commandInvocation.getCommand());
    commandExecutor.execute(commandInvocation);
  }
}
