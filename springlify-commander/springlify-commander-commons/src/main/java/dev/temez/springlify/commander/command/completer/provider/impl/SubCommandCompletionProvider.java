package dev.temez.springlify.commander.command.completer.provider.impl;

import dev.temez.springlify.commander.command.Command;
import dev.temez.springlify.commander.command.completer.provider.CompletionProvider;
import dev.temez.springlify.commander.command.filter.CommandFilterService;
import dev.temez.springlify.commander.command.invocation.CommandInvocation;
import jakarta.annotation.PostConstruct;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Unmodifiable;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * A completion provider for subcommands.
 * <p>
 * This provider suggests subcommands for a given command invocation based on the accessibility of the subcommands
 * as determined by the {@link CommandFilterService}.
 * </p>
 * <p>
 * This provider is ordered with a priority of 2.
 * </p>
 *
 * @see CommandFilterService
 * @since 0.7.0.0-RC1
 */
@Slf4j
@Order(2)
@Component
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class SubCommandCompletionProvider implements CompletionProvider {

  @NotNull
  CommandFilterService commandFilterService;

  /**
   * Initializes the completion provider. This method is called after the bean has been constructed.
   */
  @PostConstruct
  private void initialize() {
    log.debug("Initializing SubCommandCompletionProvider with order 2");
  }

  /**
   * Determines whether this provider supports the given command invocation.
   * <p>
   * This provider supports invocations with at most one argument.
   * </p>
   *
   * @param execution The command invocation to check.
   * @return {@code true} if the invocation has 0 or 1 arguments, {@code false} otherwise.
   */
  @Override
  public boolean supports(@NotNull CommandInvocation execution) {
    return execution.getArguments().size() <= 1;
  }

  /**
   * Generates a list of completion suggestions for subcommands based on the given command invocation.
   * <p>
   * This method filters the subcommands of the main command to those that are accessible to the sender and returns their names.
   * </p>
   *
   * @param execution The command invocation for which to generate suggestions.
   * @return An unmodifiable list of subcommand names that the sender can access.
   */
  @Override
  public @NotNull @Unmodifiable List<String> complete(@NotNull CommandInvocation execution) {
    return execution.getCommand().getSubcommands()
        .stream()
        .filter(subcommand -> commandFilterService.isAccessible(execution.getSender(), subcommand))
        .map(Command::getName)
        .toList();
  }
}
