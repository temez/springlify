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
public final class FloatArgumentAdapter implements ArgumentAdapter<Float> {

  @Override
  public @NotNull Float parse(@NotNull Sender<?> commandSender, @NotNull String rawArgument) throws ArgumentException {
    try {
      return Float.parseFloat(rawArgument);
    } catch (NumberFormatException e) {
      throw new ArgumentException("commander.arguments.argument-must-be-float");
    }
  }
}
