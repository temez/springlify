package dev.temez.springlify.commander.command.completer.provider.impl;

import dev.temez.springlify.commander.command.Command;
import dev.temez.springlify.commander.command.completer.provider.CompletionProvider;
import dev.temez.springlify.commander.command.invocation.CommandInvocation;
import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Unmodifiable;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.List;

@Slf4j
@Order(2)
@Component
public final class SubCommandCompletionProvider implements CompletionProvider {

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
        .map(Command::getName)
        .toList();
  }
}
