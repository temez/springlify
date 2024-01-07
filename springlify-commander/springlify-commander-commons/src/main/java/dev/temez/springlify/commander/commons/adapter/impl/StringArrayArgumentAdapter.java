package dev.temez.springlify.commander.commons.adapter.impl;

import dev.temez.springlify.commander.commons.adapter.ArgumentAdapter;
import dev.temez.springlify.commander.commons.exception.ConformableException;
import dev.temez.springlify.commander.commons.sender.Sender;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Component;

@Component
public class StringArrayArgumentAdapter implements ArgumentAdapter<String[]> {

  @Override
  public @NotNull Class<String[]> getTargetClass() {
    return String[].class;
  }

  @Override
  public String @NotNull [] parse(@NotNull Sender<?> commandSender, @NotNull String rawArgument)
      throws ConformableException {
    return new String[0];
  }
}
