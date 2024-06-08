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

/**
 * An abstract base class for completion providers that operate based on method parameters.
 * <p>
 * This class provides a framework for implementing completion providers that generate suggestions
 * based on method parameters in command invocations. Subclasses must implement the
 * {@link #supports(Parameter)} and {@link #complete(Parameter, Sender)} methods to provide
 * specific support and completion logic.
 * </p>
 *
 * @see CompletionProvider
 * @since 0.7.0.0-RC1
 */
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public abstract class MethodParameterCompletionProvider implements CompletionProvider {

  @NotNull
  CommandFilterService commandFilterService;

  /**
   * Determines whether this provider supports the given method parameter.
   *
   * @param parameter The method parameter to check.
   * @return {@code true} if this provider supports the given parameter, {@code false} otherwise.
   */
  protected abstract boolean supports(@NotNull Parameter parameter);

  /**
   * Generates a list of completion suggestions for the given method parameter and sender.
   *
   * @param parameter The method parameter for which to generate suggestions.
   * @param sender    The sender of the command invocation.
   * @return An unmodifiable list of completion suggestions.
   */
  protected abstract @NotNull @Unmodifiable List<String> complete(@NotNull Parameter parameter, @NotNull Sender<?> sender);

  /**
   * Determines whether this provider supports the given command invocation.
   *
   * @param execution The command invocation to check.
   * @return {@code true} if this provider supports the given command invocation, {@code false} otherwise.
   */
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

  /**
   * Generates a list of completion suggestions for the given command invocation.
   *
   * @param execution The command invocation for which to generate suggestions.
   * @return An unmodifiable list of completion suggestions.
   */
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
