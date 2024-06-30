package dev.temez.springlify.commander.command.executor;

import dev.temez.springlify.commander.command.Command;
import dev.temez.springlify.commander.command.executor.details.SenderDetailsResolver;
import dev.temez.springlify.commander.command.executor.provider.ParameterProvider;
import dev.temez.springlify.commander.command.invocation.CommandInvocation;
import dev.temez.springlify.commander.command.metadata.CommandInvocationMetadata;
import dev.temez.springlify.commander.command.sender.Sender;
import dev.temez.springlify.commander.exception.CommandExecutionException;
import dev.temez.springlify.commander.service.chat.CommanderChatService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.experimental.FieldDefaults;
import org.jetbrains.annotations.NotNull;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

/**
 * A command executor that uses argument providers to parse and execute commands.
 * <p>
 * This component resolves the command method and its parameters, retrieves the necessary
 * arguments from the command invocation, and invokes the command method with the resolved arguments.
 * </p>
 *
 * @see ParameterProvider
 * @see SenderDetailsResolver
 * @since 0.7.0.0-RC1
 */
@Component
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class ProviderCommandExecutor implements CommandExecutor {

  @NotNull
  List<ParameterProvider> parameterProviders;

  @NotNull
  SenderDetailsResolver senderDetailsResolver;

  @NotNull
  CommanderChatService commanderChatService;

  /**
   * Executes a command based on the provided {@link CommandInvocation}.
   * <p>
   * This method retrieves the command method and its parameters, resolves sender details,
   * parses the command arguments using argument providers, and invokes the command method
   * with the resolved arguments.
   * </p>
   *
   * @param commandInvocation The command invocation containing the details of the command to be executed.
   * @throws CommandExecutionException If an error occurs during command execution.
   */
  @Override
  @SneakyThrows
  public void execute(@NotNull CommandInvocation commandInvocation) throws CommandExecutionException {
    Command command = commandInvocation.getCommand();
    Sender<?> sender = commandInvocation.getSender();
    CommandInvocationMetadata invocationMetadata = command.getCommandInvocationMetadata();
    Method commandMethod = command.getCommandInvocationMetadata().getCommandMethod();

    if (commandMethod == null) {
      return;
    }

    if (commandInvocation.getArguments().size() < command.getCommandInvocationMetadata().getParameterCount()) {
      commanderChatService.sendErrorMessage(sender, "commander.invalid-command-usage");
      return;
    }

    List<Object> executionParameters = new ArrayList<>();
    Object senderDetails = senderDetailsResolver.resolve(command, sender);
    executionParameters.add(senderDetails);

    for (int i = 0; i < invocationMetadata.getParameterCount(); i++) {
      Parameter parameter = invocationMetadata.getParameter(i);
      String rawArgument = commandInvocation.getArguments().get(i);
      ParameterProvider parameterProvider = parameterProviders
          .stream()
          .filter(provider -> provider.supports(parameter))
          .sorted(Comparator.comparingInt(
              completionProvider -> Optional
                  .ofNullable(completionProvider.getClass().getAnnotation(Order.class))
                  .map(Order::value)
                  .orElse(0)
          ))
          .limit(1)
          .toList()
          .get(0);
      executionParameters.add(parameterProvider.parse(sender, rawArgument, parameter));
    }
    commandMethod.invoke(
        command.getCommandInvocationMetadata().getExecutor(),
        executionParameters.toArray()
    );
  }
}
