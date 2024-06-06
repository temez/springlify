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
public final class IntegerArgumentAdapter implements ArgumentAdapter<Integer> {

  @Override
  public @NotNull Integer parse(@NotNull Sender<?> commandSender, @NotNull String rawArgument) throws ArgumentException {
    try {
      return Integer.parseInt(rawArgument);
    } catch (NumberFormatException e) {
      throw new ArgumentException("commander.arguments.argument-must-be-integer");
    }
  }
}
