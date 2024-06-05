package dev.temez.springlify.commander.command.discoverer;

import dev.temez.springlify.commander.annotation.CommanderCommand;
import dev.temez.springlify.commander.command.Command;
import dev.temez.springlify.commander.command.CommandImpl;
import dev.temez.springlify.commander.command.metadata.CommandInvocationMetadata;
import dev.temez.springlify.commander.command.metadata.CommandInvocationMetadataImpl;
import dev.temez.springlify.commander.exception.CommandDiscoveryException;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.Collections;

@Slf4j
@Component
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public final class MethodBasedCommandDiscoverer implements CommandDiscoverer<Method> {

  @NotNull
  ApplicationContext applicationContext;

  @Override
  public @NotNull Command discover(@NotNull Method method) throws CommandDiscoveryException {
    Class<?> executorClass = method.getDeclaringClass();
    CommanderCommand commanderCommandAnnotation = method.getAnnotation(CommanderCommand.class);

    if (commanderCommandAnnotation == null) {
      throw new CommandDiscoveryException("Method %s is not annotated with @Command".formatted(method.getName()));
    }
    if (commanderCommandAnnotation.name().contains(" ")) {
      throw new CommandDiscoveryException("Command name %s must not contain spaces".formatted(commanderCommandAnnotation.name()));
    }

    Object executor = applicationContext.getBean(executorClass);
    CommandInvocationMetadata invocationMetadata = new CommandInvocationMetadataImpl(
        executor,
        method
    );

    log.debug("");
    log.debug("Discovered {} as method command", commanderCommandAnnotation.name());
    log.debug("Command type: {}", commanderCommandAnnotation.type());
    log.debug("Command description: {}", commanderCommandAnnotation.description());
    log.debug("");
    log.debug("Method name: {}", method.getName());
    log.debug("Execution parameters: {}", invocationMetadata.getParameters());

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
