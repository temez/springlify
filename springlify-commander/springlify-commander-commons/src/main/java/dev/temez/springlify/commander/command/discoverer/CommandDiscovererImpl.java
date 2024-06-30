package dev.temez.springlify.commander.command.discoverer;

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
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;

/**
 * Implementation of {@link CommandDiscoverer} that discovers commands.
 *
 * @since 0.7.0.0-RC1
 */
@Component
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class CommandDiscovererImpl implements CommandDiscoverer {

  /**
   * The subcommand discoverer to discover subcommands.
   */
  @NotNull
  SubcommandDiscoverer subcommandDiscoverer;

  /**
   * Discovers the command associated with the given executor.
   *
   * @param executor the executor for which to discover the command
   * @return the discovered command
   * @throws CommandDiscoveryException if an error occurs during command discovery
   */
  @Override
  public @NotNull Command discover(@NotNull Object executor) throws CommandDiscoveryException {
    Class<?> executorClass = executor.getClass();
    CommanderCommand commanderCommandAnnotation = executorClass.getAnnotation(CommanderCommand.class);

    // Check if the executor class is annotated with @Command
    if (commanderCommandAnnotation == null) {
      throw new CommandDiscoveryException("Command class %s is not annotated with @Command".formatted(executorClass.getName()));
    }

    // Check if the command name contains spaces
    if (commanderCommandAnnotation.name().contains(" ")) {
      throw new CommandDiscoveryException("Command name %s must not contain spaces".formatted(commanderCommandAnnotation.name()));
    }

    // Find the command root method
    Method commandRootMethod = Arrays.stream(executorClass.getDeclaredMethods())
        .filter(method -> method.isAnnotationPresent(CommandRoot.class))
        .findFirst()
        .orElse(null);

    // Create invocation metadata
    CommandInvocationMetadata invocationMetadata = new CommandInvocationMetadataImpl(
        executor,
        commandRootMethod
    );

    // Discover subcommands
    List<Command> subcommands = subcommandDiscoverer.discover(executor);

    // Throw an exception if there is no root command method and no subcommands
    if (invocationMetadata.getCommandMethod() == null && subcommands.isEmpty()) {
      throw new CommandDiscoveryException("Command class %s has no root command method and no subcommands".formatted(executorClass.getName()));
    }

    // Create and return the command
    return new CommandImpl(
        commanderCommandAnnotation.name(),
        commanderCommandAnnotation.description(),
        Arrays.asList(commanderCommandAnnotation.alias()),
        commanderCommandAnnotation.type(),
        subcommands,
        invocationMetadata
    );
  }
}

