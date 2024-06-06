package dev.temez.springlify.commander.command.executor;

import dev.temez.springlify.commander.command.Command;
import dev.temez.springlify.commander.command.executor.details.SenderDetailsResolver;
import dev.temez.springlify.commander.command.executor.provider.ArgumentProvider;
import dev.temez.springlify.commander.command.invocation.CommandInvocation;
import dev.temez.springlify.commander.command.metadata.CommandInvocationMetadata;
import dev.temez.springlify.commander.command.sender.Sender;
import dev.temez.springlify.commander.exception.CommandExecutionException;
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

@Component
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class ProviderCommandExecutor implements CommandExecutor {

  @NotNull
  List<ArgumentProvider> argumentProviders;

  @NotNull
  SenderDetailsResolver senderDetailsResolver;

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
      //TODO help messages
      return;
    }

    List<Object> executionParameters = new ArrayList<>();
    Object senderDetails = senderDetailsResolver.resolve(commandMethod, sender);
    executionParameters.add(senderDetails);

    for (int i = 0; i < invocationMetadata.getParameterCount(); i++) {
      Parameter parameter = invocationMetadata.getParameter(i);
      String rawArgument = commandInvocation.getArguments().get(i);
      ArgumentProvider argumentProvider = argumentProviders
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
      executionParameters.add(argumentProvider.parse(sender, rawArgument, parameter));
    }
    commandMethod.invoke(
        command.getCommandInvocationMetadata().getExecutor(),
        executionParameters.toArray()
    );
  }
}
