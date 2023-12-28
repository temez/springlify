package dev.temez.springlify.commander.commons.completer;

import dev.temez.springlify.commander.commons.adapter.ArgumentAdapter;
import dev.temez.springlify.commander.commons.adapter.ArgumentAdapterFactory;
import dev.temez.springlify.commander.commons.annotation.Adapter;
import dev.temez.springlify.commander.commons.command.RegisteredCommand;
import dev.temez.springlify.commander.commons.execution.CommandExecution;
import dev.temez.springlify.commander.commons.execution.preprocessor.chain.ExecutionPreprocessorChain;
import dev.temez.springlify.commander.commons.sender.Sender;
import dev.temez.springlify.commander.commons.validaiton.CommandFilterService;
import java.lang.reflect.Parameter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Unmodifiable;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

/**
 * Implementation of the CommandCompleter interface for completing command-related tasks.
 *
 * @since 0.5.8.9dev
 */
@Component
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public final class CommandCompleterImpl implements CommandCompleter {

  @NotNull
  ApplicationContext applicationContext;

  @NotNull
  ArgumentAdapterFactory argumentAdapterFactory;

  @NotNull
  CommandFilterService commandFilterService;

  @NotNull
  ExecutionPreprocessorChain preprocessorChain;

  /**
   * {@inheritDoc}
   */
  @Override
  public @NotNull List<String> complete(@NotNull CommandExecution execution) {
    preprocessorChain.process(execution);
    Sender<?> sender = execution.getCommandSender();
    RegisteredCommand command = execution.getCommand();
    List<String> result = new ArrayList<>();
    if (execution.getArguments().size() <= 1) {
      result.addAll(getSubcommandSuggestions(execution));
    }
    if (!commandFilterService.isAccessible(sender, command)) {
      return result;
    }
    if (execution.getArguments().size() > command.getExecutionContext().getParametersCount()) {
      return result;
    }
    result.addAll(getAdapterSuggestions(execution));
    return result;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public @NotNull @Unmodifiable List<String> completeSorted(
      @NotNull CommandExecution commandExecution
  ) {
    List<String> result = new ArrayList<>(complete(commandExecution));
    if (!commandExecution.getArguments().isEmpty()) {
      result.removeIf(
          s -> !s.toLowerCase().startsWith(commandExecution.getLastArgument().toLowerCase()));
    }
    Collections.sort(result);
    return List.copyOf(result);
  }

  private @NotNull List<String> getExternalAdapterSuggestions(@NotNull CommandExecution execution) {
    RegisteredCommand command = execution.getCommand();
    Parameter parameter = command
        .getExecutionContext()
        .getParameter(execution.getLastArgumentIndex());
    Adapter adapterAnnotation = parameter.getAnnotation(Adapter.class);
    if (adapterAnnotation == null) {
      return Collections.emptyList();
    }
    return applicationContext
        .getBean(adapterAnnotation.value())
        .complete(execution.getCommandSender());
  }

  private @NotNull List<String> getAdapterSuggestions(@NotNull CommandExecution execution) {
    List<String> externalAdapterSuggestions = getExternalAdapterSuggestions(execution);
    if (!externalAdapterSuggestions.isEmpty()) {
      return externalAdapterSuggestions;
    }
    RegisteredCommand command = execution.getCommand();
    Parameter parameter = command
        .getExecutionContext()
        .getParameter(execution.getLastArgumentIndex());
    ArgumentAdapter<?> adapter = argumentAdapterFactory.get(parameter.getType());
    List<String> result = adapter.complete(execution.getCommandSender());
    if (!result.isEmpty()) {
      return result;
    }
    List<String> newResult = new ArrayList<>();
    newResult.add(String.format("<%s>", parameter.getName()));
    newResult.addAll(result);
    return newResult;
  }

  private @NotNull List<String> getSubcommandSuggestions(@NotNull CommandExecution execution) {
    return execution.getCommand().getSubcommands()
        .stream()
        .filter(subcommand -> commandFilterService.isAccessible(
            execution.getCommandSender(),
            subcommand
        ))
        .map(RegisteredCommand::getName)
        .toList();
  }
}
