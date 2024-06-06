package dev.temez.springlify.commander.argument.adapter.impl;

import dev.temez.springlify.commander.argument.adapter.ArgumentAdapter;
import dev.temez.springlify.commander.command.sender.Sender;
import dev.temez.springlify.commander.exception.argument.ArgumentException;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public final class StringArgumentAdapter implements ArgumentAdapter<String> {

  @Override
  public @NotNull String parse(@NotNull Sender<?> commandSender, @NotNull String rawArgument) throws ArgumentException {
    return rawArgument;
  }
}
