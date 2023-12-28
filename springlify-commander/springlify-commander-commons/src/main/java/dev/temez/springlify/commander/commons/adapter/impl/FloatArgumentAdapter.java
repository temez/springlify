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
public final class FloatArgumentAdapter implements ArgumentAdapter<Float> {

  @Override
  public @NotNull Class<Float> getTargetClass() {
    return Float.class;
  }

  @Override
  public @NotNull Float parse(
      @NotNull Sender<?> commandSender,
      @NotNull String rawArgument
  ) throws ConformableException {
    try {
      return Float.parseFloat(rawArgument);
    } catch (Exception e) {
      throw new ConformableException("commander.arguments.argument-must-be-float");
    }
  }
}
