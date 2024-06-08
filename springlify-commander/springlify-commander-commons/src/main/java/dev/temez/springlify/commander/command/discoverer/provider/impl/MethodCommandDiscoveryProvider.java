package dev.temez.springlify.commander.command.discoverer.provider.impl;

import dev.temez.springlify.commander.annotation.CommanderCommand;
import dev.temez.springlify.commander.command.Command;
import dev.temez.springlify.commander.command.CommandImpl;
import dev.temez.springlify.commander.command.discoverer.provider.CommandDiscoveryProvider;
import dev.temez.springlify.commander.command.metadata.CommandInvocationMetadata;
import dev.temez.springlify.commander.command.metadata.CommandInvocationMetadataImpl;
import dev.temez.springlify.commander.exception.CommandException;
import dev.temez.springlify.commander.exception.discovery.CommandDiscoveryException;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Unmodifiable;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Implementation of {@link CommandDiscoveryProvider} that discovers commands from methods
 * annotated with {@link CommanderCommand}.
 *
 * @since 0.7.0.0-RC1
 */
@Component
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public final class MethodCommandDiscoveryProvider implements CommandDiscoveryProvider {

  @NotNull
  ApplicationContext applicationContext;

  /**
   * Checks if this provider supports the given command class.
   * This provider supports classes that have methods annotated with {@link CommanderCommand}.
   *
   * @param commandClass The class to check.
   * @return {@code true} if this provider supports the given class, {@code false} otherwise.
   */
  @Override
  public boolean supports(@NotNull Class<?> commandClass) {
    return Arrays.stream(commandClass.getDeclaredMethods())
        .anyMatch(method -> method.isAnnotationPresent(CommanderCommand.class));
  }

  /**
   * Discovers and creates a list of {@link Command} objects from the given command class.
   * This method processes methods annotated with {@link CommanderCommand}.
   *
   * @param commandClass The class containing the command logic.
   * @return An unmodifiable list of discovered commands.
   * @throws CommandDiscoveryException If an error occurs during command discovery.
   */
  @Override
  public @NotNull @Unmodifiable List<Command> discover(@NotNull Class<?> commandClass) throws CommandDiscoveryException {
    return Arrays.stream(commandClass.getDeclaredMethods())
        .filter(method -> method.isAnnotationPresent(CommanderCommand.class))
        .map(this::createCommand)
        .toList();
  }

  /**
   * Creates a {@link Command} from a given method annotated with {@link CommanderCommand}.
   *
   * @param method The method annotated with {@link CommanderCommand}.
   * @return The created command.
   * @throws CommandException If the command name contains spaces.
   */
  private @NotNull Command createCommand(@NotNull Method method) {
    Class<?> executorClass = method.getDeclaringClass();
    CommanderCommand commanderCommandAnnotation = method.getAnnotation(CommanderCommand.class);

    if (commanderCommandAnnotation.name().contains(" ")) {
      throw new CommandException("Command name %s must not contain spaces".formatted(commanderCommandAnnotation.name()));
    }

    Object executor = applicationContext.getBean(executorClass);
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
