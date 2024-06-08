package dev.temez.springlify.commander.exception.handler;

import dev.temez.springlify.commander.command.invocation.CommandInvocation;
import dev.temez.springlify.commander.exception.CommanderException;
import dev.temez.springlify.commander.service.chat.CommanderChatService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class CommanderExceptionHandlerImpl implements CommanderExceptionHandler {

  @NotNull
  CommanderChatService commanderChatService;

  @Override
  public void handle(@NotNull CommandInvocation invocation, @NotNull CommanderException exception) {
    commanderChatService.sendErrorMessage(invocation.getSender(), exception.getMessage());
  }
}
