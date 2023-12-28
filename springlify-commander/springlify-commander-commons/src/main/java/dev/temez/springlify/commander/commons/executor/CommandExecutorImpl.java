package dev.temez.springlify.commander.commons.executor;

import dev.temez.springlify.commander.commons.adapter.ArgumentAdapterFactory;
import dev.temez.springlify.commander.commons.annotation.Adapter;
import dev.temez.springlify.commander.commons.annotation.EnableDetailsSupport;
import dev.temez.springlify.commander.commons.chat.CommanderChatService;
import dev.temez.springlify.commander.commons.command.RegisteredCommand;
import dev.temez.springlify.commander.commons.exception.ConformableException;
import dev.temez.springlify.commander.commons.execution.CommandExecution;
import dev.temez.springlify.commander.commons.execution.preprocessor.chain.ExecutionPreprocessorChain;
import dev.temez.springlify.commander.commons.help.CommandHelpService;
import dev.temez.springlify.commander.commons.sender.Sender;
import dev.temez.springlify.commander.commons.sender.SenderDetailsFactory;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.ArrayList;
import java.util.List;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.experimental.FieldDefaults;
import org.jetbrains.annotations.NotNull;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public final class CommandExecutorImpl implements CommandExecutor {

  @NotNull ApplicationContext applicationContext;

  @NotNull ArgumentAdapterFactory argumentAdapterFactory;

  @NotNull ExecutionPreprocessorChain preprocessorChain;

  @NotNull CommanderChatService chatService;

  @NotNull CommandHelpService commandHelpService;

  @Override
  @SneakyThrows
  public void execute(@NotNull CommandExecution execution) throws ConformableException {
    preprocessorChain.process(execution);
    Sender<?> sender = execution.getCommandSender();
    RegisteredCommand command = execution.getCommand();

    if (execution.getArguments().size() != command.getExecutionContext().getParametersCount()) {
      chatService.sendErrorMessage(sender, "commander.help.invalid-command-usage");
      commandHelpService.sendHelpMessage(sender, command);
      return;
    }

    List<Object> executionParameters = new ArrayList<>();
    executionParameters.add(getSenderDetails(sender, command));

    Method commandMethod = command.getExecutionContext().getMethod();

    for (int i = 0; i < command.getExecutionContext().getParametersCount(); i++) {
      Parameter parameter = command.getExecutionContext().getParameter(i);
      String rawArgument = execution.getArguments().get(i);
      executionParameters.add(parseArgument(parameter, rawArgument, execution.getCommandSender()));
    }

    commandMethod.invoke(
        command.getExecutionContext().getCommandExecutor(),
        executionParameters.toArray()
    );
  }

  private @NotNull Object parseArgument(
      @NotNull Parameter parameter,
      @NotNull String rawArgument,
      @NotNull Sender<?> sender
  ) {
    Adapter adapterAnnotation = parameter.getAnnotation(Adapter.class);
    if (adapterAnnotation != null) {
      return applicationContext.getBean(adapterAnnotation.value()).parse(sender, rawArgument);
    }
    return argumentAdapterFactory.get(parameter.getType()).parse(sender, rawArgument);
  }

  private @NotNull Object getSenderDetails(
      @NotNull Sender<?> sender,
      @NotNull RegisteredCommand command
  ) {
    EnableDetailsSupport detailsSupport = command
        .getExecutionContext()
        .getMethod()
        .getAnnotation(EnableDetailsSupport.class);

    if (detailsSupport != null) {
      SenderDetailsFactory<?> detailsFactory = applicationContext.getBean(detailsSupport.value());
      return detailsFactory.get(sender);
    }
    return sender.getPlatformSender();
  }
}
