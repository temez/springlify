package dev.temez.springlify.commander.command.completer.provider;

import dev.temez.springlify.commander.command.filter.CommandFilterService;
import dev.temez.springlify.commander.command.invocation.CommandInvocation;
import dev.temez.springlify.commander.command.metadata.CommandInvocationMetadata;
import dev.temez.springlify.commander.command.sender.Sender;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Unmodifiable;

import java.lang.reflect.Parameter;
import java.util.List;

@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public abstract class MethodParameterCompletionProvider implements CompletionProvider {

  @NotNull
  CommandFilterService commandFilterService;

  protected abstract boolean supports(@NotNull Parameter parameter);

  protected abstract @NotNull @Unmodifiable List<String> complete(@NotNull Parameter parameter, @NotNull Sender<?> sender);

  @Override
  public boolean supports(@NotNull CommandInvocation execution) {
    CommandInvocationMetadata invocationMetadata = execution.getCommand().getCommandInvocationMetadata();
    if (invocationMetadata.getCommandMethod() == null) {
      return false;
    }
    if (invocationMetadata.getParameterCount() == 0) {
      return false;
    }
    if (execution.getArguments().size() > invocationMetadata.getParameterCount()) {
      return false;
    }
    if (!commandFilterService.isAccessible(execution.getSender(), execution.getCommand())) {
      return false;
    }
    int lastIndex = execution.getLastArgumentIndex();
    Parameter parameter = invocationMetadata.getParameter(lastIndex);
    return supports(parameter);
  }

  @Override
  public @NotNull @Unmodifiable List<String> complete(@NotNull CommandInvocation execution) {
    int lastIndex = execution.getLastArgumentIndex();
    CommandInvocationMetadata invocationMetadata = execution.getCommand().getCommandInvocationMetadata();
    Parameter parameter = invocationMetadata.getParameter(lastIndex);
    List<String> result = complete(parameter, execution.getSender());
    if (!result.isEmpty()) {
      return result;
    }
    return List.of(String.format("<%s>", parameter.getName()));
  }
}