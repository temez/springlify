package dev.temez.springlify.commander.exception.handler.impl;

import dev.temez.springlify.commander.command.invocation.CommandInvocation;
import dev.temez.springlify.commander.exception.filter.CommandFilterException;
import dev.temez.springlify.commander.exception.handler.CommandExceptionHandler;
import dev.temez.springlify.commander.service.chat.CommanderChatService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class CommandFilterExceptionHandler implements CommandExceptionHandler {

  @NotNull
  CommanderChatService commanderChatService;

  @Override
  public void handle(@NotNull CommandInvocation invocation, @NotNull Exception exception) {
    commanderChatService.sendErrorMessage(invocation.getSender(), exception.getMessage());
  }

  @Override
  public boolean supports(@NotNull Class<? extends Exception> exceptionClass) {
    return exceptionClass.equals(CommandFilterException.class);
  }
}
