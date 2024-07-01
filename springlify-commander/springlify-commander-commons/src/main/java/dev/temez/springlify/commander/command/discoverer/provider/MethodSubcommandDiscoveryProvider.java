package dev.temez.springlify.commander.command.discoverer.provider;

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
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * {@code MethodSubcommandDiscoveryProvider} is responsible for discovering subcommands based on methods annotated
 * with {@link CommanderCommand}.
 *
 * @since 0.7.0.0-RC1
 */
@Component
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class MethodSubcommandDiscoveryProvider implements SubcommandDiscoveryProvider {

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
  public @NotNull List<Command> discover(@NotNull Object executor) throws CommandDiscoveryException {
    Class<?> executorClass = executor.getClass();
    return Arrays.stream(executorClass.getDeclaredMethods())
        .filter(method -> method.isAnnotationPresent(CommanderCommand.class))
        .map(method -> createCommand(executor, method))
        .toList();
  }

  /**
   * Creates a command based on the provided executor and method.
   *
   * @param executor the executor associated with the command
   * @param method   the method annotated with {@link CommanderCommand}
   * @return the created command
   * @throws CommandDiscoveryException if an error occurs during command creation
   */
  private @NotNull Command createCommand(@NotNull Object executor, @NotNull Method method) throws CommandDiscoveryException {
    CommanderCommand commanderCommandAnnotation = method.getAnnotation(CommanderCommand.class);
    if (commanderCommandAnnotation.name().contains(" ")) {
      throw new CommandDiscoveryException("Command name %s must not contain spaces".formatted(commanderCommandAnnotation.name()));
    }
    CommandInvocationMetadata invocationMetadata = new CommandInvocationMetadataImpl(
        executor,
        method
    );

    return new CommandImpl(
        commanderCommandAnnotation.name(),
        commanderCommandAnnotation.description(),
        Collections.emptyList(),
        commanderCommandAnnotation.type(),
        Collections.emptyList(),
        invocationMetadata
    );
  }
}
