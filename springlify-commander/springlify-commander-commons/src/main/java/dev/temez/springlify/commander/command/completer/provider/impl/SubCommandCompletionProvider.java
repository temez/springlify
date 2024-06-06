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

@Slf4j
@Order(2)
@Component
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public final class SubCommandCompletionProvider implements CompletionProvider {

  @NotNull
  CommandFilterService commandFilterService;

  @PostConstruct
  private void initialize() {
    log.debug("Initializing SubCommandCompletionProvider with order 2");
  }

  @Override
  public boolean supports(@NotNull CommandInvocation execution) {
    return execution.getArguments().size() <= 1;
  }

  @Override
  public @NotNull @Unmodifiable List<String> complete(@NotNull CommandInvocation execution) {
    return execution.getCommand().getSubcommands()
        .stream()
        .filter(subcommand -> commandFilterService.isAccessible(execution.getSender(), subcommand))
        .map(Command::getName)
        .toList();
  }
}
