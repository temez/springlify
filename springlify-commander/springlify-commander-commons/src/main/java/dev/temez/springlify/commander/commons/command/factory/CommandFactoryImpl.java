package dev.temez.springlify.commander.commons.command.factory;

import dev.temez.springlify.commander.commons.annotation.Command;
import dev.temez.springlify.commander.commons.annotation.RootCommand;
import dev.temez.springlify.commander.commons.annotation.SubCommand;
import dev.temez.springlify.commander.commons.command.CommandExecutionContext;
import dev.temez.springlify.commander.commons.command.CommandValidationContext;
import dev.temez.springlify.commander.commons.command.RegisteredCommand;
import dev.temez.springlify.commander.commons.validaiton.factory.CommandFilterFactory;
import dev.temez.springlify.platform.commons.exception.FormattedException;
import java.lang.reflect.Method;
import java.util.Arrays;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.log4j.Log4j2;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Component;

/**
 * Implementation of {@link CommandFactory} that creates {@link RegisteredCommand}
 * instances from annotated command classes.
 *
 * @since 0.5.8.9dev
 */
@Log4j2
@Component
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public final class CommandFactoryImpl implements CommandFactory {

  @NotNull CommandFilterFactory commandFilterFactory;

  /**
   * {@inheritDoc}
   */
  @Override
  public @NotNull RegisteredCommand get(@NotNull Object command) throws FormattedException {
    Command commandAnnotation = command.getClass().getAnnotation(Command.class);
    if (commandAnnotation == null) {
      throw new FormattedException("No @Command annotation found");
    }

    log.info("Discovered {} command", commandAnnotation.name());
    Method rootCommandMethod = Arrays.stream(command.getClass().getDeclaredMethods())
        .filter(method -> method.isAnnotationPresent(RootCommand.class))
        .findFirst()
        .orElseThrow(
            () -> new FormattedException("No root command method found in %s",
                command.getClass().getSimpleName()
            )
        );

    RegisteredCommand rootCommand = RegisteredCommand
        .builder()
        .executionContext(
            CommandExecutionContext.builder()
                .method(rootCommandMethod)
                .commandExecutor(command)
                .build()
        )
        .validationContext(
            CommandValidationContext.builder()
                .simpleCommandFilters(commandFilterFactory.getSimpleFilters(rootCommandMethod))
                .filterAnnotations(commandFilterFactory.getFilterAnnotations(rootCommandMethod))
                .build()
        )
        .description(commandAnnotation.description())
        .name(commandAnnotation.name())
        .alias(Arrays.stream(commandAnnotation.alias()).toList())
        .type(commandAnnotation.type())
        .build();

    Arrays.stream(command.getClass().getDeclaredMethods())
        .filter(method -> method.isAnnotationPresent(SubCommand.class))
        .map(method -> {
          SubCommand subCommand = method.getAnnotation(SubCommand.class);
          log.info(
              "Discovered subcommand {} for command {}",
              commandAnnotation.name(),
              subCommand.name()
          );
          return RegisteredCommand.builder()
              .executionContext(
                  CommandExecutionContext.builder()
                      .method(method)
                      .commandExecutor(command)
                      .build()
              )
              .parentCommand(rootCommand)
              .validationContext(
                  CommandValidationContext.builder()
                      .simpleCommandFilters(commandFilterFactory.getSimpleFilters(method))
                      .filterAnnotations(commandFilterFactory.getFilterAnnotations(method))
                      .build()
              )
              .description(subCommand.description())
              .name(subCommand.name())
              .type(subCommand.type())
              .build();
        })
        .forEach(subcommand -> rootCommand.getSubcommands().add(subcommand));

    return rootCommand;
  }
}
