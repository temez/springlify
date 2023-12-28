package dev.temez.springlify.commander.commons.adapter.impl;

import dev.temez.springlify.commander.commons.adapter.ArgumentAdapter;
import dev.temez.springlify.commander.commons.exception.ConformableException;
import dev.temez.springlify.commander.commons.sender.Sender;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public final class IntgerArgumentAdapter implements ArgumentAdapter<Integer> {

  @Override
  public @NotNull Class<Integer> getTargetClass() {
    return Integer.class;
  }

  @Override
  public @NotNull Integer parse(
      @NotNull Sender<?> commandSender,
      @NotNull String rawArgument
  ) throws ConformableException {
    try {
      return Integer.parseInt(rawArgument);
    } catch (Exception e) {
      throw new ConformableException("commander.arguments.argument-must-be-integer");
    }
  }
}
