package dev.temez.springlify.commander.command.discoverer.provider;

import dev.temez.springlify.commander.annotation.CommandRoot;
import dev.temez.springlify.commander.annotation.CommanderCommand;
import dev.temez.springlify.commander.command.Command;
import dev.temez.springlify.commander.command.CommandImpl;
import dev.temez.springlify.commander.command.metadata.CommandInvocationMetadata;
import dev.temez.springlify.commander.command.metadata.CommandInvocationMetadataImpl;
import dev.temez.springlify.commander.exception.discovery.CommandDiscoveryException;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * {@code ClassSubCommandDiscoveryProvider} is responsible for discovering subcommands based on inner classes annotated
 * with {@link CommanderCommand}.
 *
 * @since 0.7.0.0-RC1
 */
@Component
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public final class ClassSubCommandDiscoveryProvider implements SubcommandDiscoveryProvider {

  /**
   * The application context.
   */
  @NotNull
  ApplicationContext applicationContext;

  /**
   * The subcommand discovery provider.
   */
  @NotNull
  @Qualifier("methodSubcommandDiscoveryProvider")
  SubcommandDiscoveryProvider subcommandDiscoveryProvider;

  /**
   * {@inheritDoc}
   */
  @Override
  public boolean supports(@NotNull Object executor) {
    return true;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public @NotNull List<Command> discover(@NotNull Object executor) {
    return Arrays.stream(executor.getClass().getDeclaredClasses())
        .filter(executorClass -> executorClass.isAnnotationPresent(CommanderCommand.class))
        .map(executorClass -> {
          CommanderCommand commanderCommandAnnotation = executorClass.getAnnotation(CommanderCommand.class);
          if (commanderCommandAnnotation.name().contains(" ")) {
            throw new CommandDiscoveryException("Command name %s must not contain spaces".formatted(commanderCommandAnnotation.name()));
          }
          Object subcommandExecutor = applicationContext.getBean(executorClass);
          Method commandRootMethod = Arrays.stream(executorClass.getDeclaredMethods())
              .filter(method -> method.isAnnotationPresent(CommandRoot.class))
              .findFirst()
              .orElse(null);

          CommandInvocationMetadata invocationMetadata = new CommandInvocationMetadataImpl(
              subcommandExecutor,
              commandRootMethod
          );

          List<Command> subcommands = new ArrayList<>();

          subcommands.addAll(discover(subcommandExecutor));

          subcommands.addAll(subcommandDiscoveryProvider.discover(subcommandExecutor));

          if (invocationMetadata.getCommandMethod() == null && subcommands.isEmpty()) {
            throw new CommandDiscoveryException("Command class %s has no root command method and no subcommands".formatted(executorClass.getName()));
          }

          return (Command) new CommandImpl(
              commanderCommandAnnotation.name(),
              commanderCommandAnnotation.description(),
              Arrays.stream(commanderCommandAnnotation.alias()).toList(),
              commanderCommandAnnotation.type(),
              subcommands,
              invocationMetadata
          );
        })
        .toList();
  }
}
