package dev.temez.springlify.commander.command.discoverer;

import dev.temez.springlify.commander.annotation.CommanderCommand;
import dev.temez.springlify.commander.annotation.CommandRoot;
import dev.temez.springlify.commander.command.Command;
import dev.temez.springlify.commander.command.CommandImpl;
import dev.temez.springlify.commander.command.metadata.CommandInvocationMetadata;
import dev.temez.springlify.commander.command.metadata.CommandInvocationMetadataImpl;
import dev.temez.springlify.commander.exception.CommandDiscoveryException;
import dev.temez.springlify.commander.exception.CommandException;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

@Slf4j
@Component
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public final class ClassBasedCommandDiscoverer implements CommandDiscoverer<Class<?>> {

  @NotNull
  ApplicationContext applicationContext;

  @NotNull
  CommandDiscoverer<Method> methodCommandDiscoverer;

  @Override
  public @NotNull Command discover(@NotNull Class<?> executorClass) throws CommandDiscoveryException {
    CommanderCommand commanderCommandAnnotation = executorClass.getAnnotation(CommanderCommand.class);

    if (commanderCommandAnnotation == null) {
      throw new CommandException("Command class %s is not annotated with @Command".formatted(executorClass.getName()));
    }
    if (commanderCommandAnnotation.name().contains(" ")) {
      throw new CommandException("Command name %s must not contain spaces".formatted(commanderCommandAnnotation.name()));
    }

    Object executor = applicationContext.getBean(executorClass);
    Method commandRootMethod = Arrays.stream(executorClass.getDeclaredMethods())
        .filter(method -> method.isAnnotationPresent(CommandRoot.class))
        .findFirst()
        .orElse(null);

    CommandInvocationMetadata invocationMetadata = new CommandInvocationMetadataImpl(
        executor,
        commandRootMethod
    );

    log.debug("");
    log.debug("Discovered {} as class command", commanderCommandAnnotation.name());
    log.debug("Command type: {}", commanderCommandAnnotation.type());
    log.debug("Command description: {}", commanderCommandAnnotation.description());
    log.debug("Command alias {}", Arrays.asList(commanderCommandAnnotation.alias()));
    if (invocationMetadata.getCommandMethod() != null) {
      log.debug("");
      log.debug("Method name: {}", invocationMetadata.getCommandMethod().getName());
      log.debug("Execution parameters: {}", invocationMetadata.getParameters());
    }

    Stream<Command> methodBasedDiscoveryResult = Arrays
        .stream(executorClass.getDeclaredMethods())
        .filter(method -> method.isAnnotationPresent(CommanderCommand.class))
        .map(methodCommandDiscoverer::discover);

    Stream<Command> classBasedDiscoveryResult = Arrays
        .stream(executorClass.getDeclaredClasses())
        .map(this::discover);

    List<Command> subcommands = Stream
        .concat(methodBasedDiscoveryResult, classBasedDiscoveryResult)
        .toList();

    if (invocationMetadata.getCommandMethod() == null && subcommands.isEmpty()) {
      throw new CommandException("Command class %s has no root command method and no subcommands".formatted(executorClass.getName()));
    }

    return new CommandImpl(
        commanderCommandAnnotation.name(),
        commanderCommandAnnotation.description(),
        Arrays.stream(commanderCommandAnnotation.alias()).toList(),
        commanderCommandAnnotation.type(),
        subcommands,
        invocationMetadata
    );
  }
}
