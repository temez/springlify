package dev.temez.springlify.commander.command.discoverer.provider.impl;

import dev.temez.springlify.commander.annotation.CommandRoot;
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
import java.util.*;

/**
 * Implementation of {@link CommandDiscoveryProvider} that discovers commands from classes
 * annotated with {@link CommanderCommand}.
 *
 * @since 0.7.0.0-RC1
 */
@Component
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public final class AnnotationClassCommandDiscoveryProvider implements CommandDiscoveryProvider {

  @NotNull
  ApplicationContext applicationContext;

  @NotNull
  MethodCommandDiscoveryProvider methodCommandDiscoveryProvider;

  /**
   * Checks if this provider supports the given command class.
   * This provider supports classes annotated with {@link CommanderCommand}.
   *
   * @param commandClass The class to check.
   * @return {@code true} if this provider supports the given class, {@code false} otherwise.
   */
  @Override
  public boolean supports(@NotNull Class<?> commandClass) {
    return commandClass.isAnnotationPresent(CommanderCommand.class);
  }

  /**
   * Discovers and creates a list of {@link Command} objects from the given command class.
   * This method processes classes annotated with {@link CommanderCommand}.
   *
   * @param commandClass The class containing the command logic.
   * @return An unmodifiable list of discovered commands.
   * @throws CommandDiscoveryException If an error occurs during command discovery.
   */
  @Override
  public @NotNull @Unmodifiable List<Command> discover(@NotNull Class<?> commandClass) throws CommandDiscoveryException {
    CommanderCommand commanderCommandAnnotation = commandClass.getAnnotation(CommanderCommand.class);

    if (commanderCommandAnnotation.name().contains(" ")) {
      throw new CommandException("Command name %s must not contain spaces".formatted(commanderCommandAnnotation.name()));
    }

    Method commandRootMethod = Arrays.stream(commandClass.getDeclaredMethods())
        .filter(method -> method.isAnnotationPresent(CommandRoot.class))
        .findFirst()
        .orElse(null);
    Object executor = applicationContext.getBean(commandClass);

    CommandInvocationMetadata invocationMetadata = new CommandInvocationMetadataImpl(
        executor,
        commandRootMethod
    );

    List<Command> discoveredSubcommands = new ArrayList<>();
    if (methodCommandDiscoveryProvider.supports(commandClass)) {
      discoveredSubcommands.addAll(methodCommandDiscoveryProvider.discover(commandClass));
    }
    discoveredSubcommands.addAll(Arrays.stream(commandClass.getDeclaredClasses())
        .filter(this::supports)
        .map(this::discover)
        .flatMap(Collection::stream)
        .toList()
    );

    Command command = new CommandImpl(
        commanderCommandAnnotation.name(),
        commanderCommandAnnotation.description(),
        Collections.emptyList(),
        commanderCommandAnnotation.type(),
        discoveredSubcommands,
        invocationMetadata
    );
    return Collections.singletonList(command);
  }
}
