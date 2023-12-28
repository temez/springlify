package dev.temez.springlify.commander.commons.exception.handler;

import dev.temez.springlify.commander.commons.chat.CommanderChatService;
import dev.temez.springlify.commander.commons.exception.ConformableException;
import dev.temez.springlify.commander.commons.execution.CommandExecution;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public final class CommanderExceptionHandlerImpl implements CommanderExceptionHandler {

  @NotNull CommanderChatService chatService;

  @Override
  public void handle(@NotNull CommandExecution execution, @NotNull ConformableException exception) {
    chatService.sendErrorMessage(
        execution.getCommandSender(),
        exception.getMessage(),
        exception.getReplacers()
    );
  }
}
