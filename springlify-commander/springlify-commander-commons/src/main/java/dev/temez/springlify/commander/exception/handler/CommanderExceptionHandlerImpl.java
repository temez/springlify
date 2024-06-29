package dev.temez.springlify.commander.exception.handler;

import dev.temez.springlify.commander.command.invocation.CommandInvocation;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class CommanderExceptionHandlerImpl implements CommanderExceptionHandler {

  @NotNull
  List<CommandExceptionHandler> exceptionHandlers;

  @Override
  public void handle(@NotNull CommandInvocation invocation, @NotNull Exception exception) throws RuntimeException {
    List<CommandExceptionHandler> handlers = exceptionHandlers.stream()
        .filter(commandExceptionHandler -> commandExceptionHandler.supports(exception.getClass()))
        .toList();
    if (handlers.isEmpty()) {
      throw new RuntimeException(exception.getMessage(), exception);
    }
    handlers.forEach(handler -> handler.handle(invocation, exception));
  }
}
