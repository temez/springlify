package dev.temez.springlify.commander.commons.adapter;

import dev.temez.springlify.commander.commons.completer.ArgumentCompleter;
import dev.temez.springlify.commander.commons.exception.ConformableException;
import dev.temez.springlify.commander.commons.sender.Sender;
import java.util.Collections;
import java.util.List;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Unmodifiable;

public interface ArgumentAdapter<T> extends ArgumentCompleter {

  @Override
  default @Unmodifiable @NotNull List<String> complete(@NotNull Sender<?> commandSender) {
    return Collections.emptyList();
  }

  @NotNull
  Class<T> getTargetClass();

  @NotNull
  T parse(
      @NotNull Sender<?> commandSender,
      @NotNull String rawArgument
  ) throws ConformableException;
}

