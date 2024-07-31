package dev.temez.springlify.commander.service;

import dev.temez.springlify.commander.command.completer.CommandCompleter;
import dev.temez.springlify.commander.command.executor.CommandExecutor;
import dev.temez.springlify.commander.command.filter.CommandFilterService;
import dev.temez.springlify.commander.command.invocation.CommandInvocation;
import dev.temez.springlify.commander.command.preprocessor.ExecutionPreprocessorService;
import dev.temez.springlify.commander.exception.handler.CommanderExceptionHandler;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

/**
 * Implementation of {@link CommandService}.
 *
 * @since 0.7.0.0-RC1
 */
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

  @NotNull
  CommanderExceptionHandler exceptionHandler;

  /**
   * Completes the command invocation based on the provided command invocation.
   * Preprocesses the invocation and delegates completion to the command completer.
   *
   * @param commandInvocation The command invocation to complete.
   * @return An unmodifiable list of completion suggestions.
   */
  @Override
  public @NotNull List<String> complete(@NotNull CommandInvocation commandInvocation) {
    executionPreprocessorService.process(commandInvocation);
    return commandCompleter.completeSorted(commandInvocation);
  }

  /**
   * Executes the command invocation.
   * Preprocesses the invocation, filters the command, and executes it.
   * Handles any exceptions that occur during execution.
   *
   * @param commandInvocation The command invocation to execute.
   */
  @Override
  public void execute(@NotNull CommandInvocation commandInvocation) {
    executionPreprocessorService.process(commandInvocation);
    try {
      commandFilterService.filter(commandInvocation.getSender(), commandInvocation.getCommand());
      commandExecutor.execute(commandInvocation);
    } catch (InvocationTargetException exception) {
      exceptionHandler.handle(commandInvocation, (Exception) exception.getCause());
    } catch (Exception exception) {
      exceptionHandler.handle(commandInvocation, exception);
    }
  }
}

