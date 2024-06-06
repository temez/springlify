package dev.temez.springlify.commander.argument.adapter;

import dev.temez.springlify.commander.argument.completer.ArgumentCompleter;
import dev.temez.springlify.commander.command.sender.Sender;
import dev.temez.springlify.commander.exception.argument.ArgumentException;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Unmodifiable;

import java.util.Collections;
import java.util.List;


public interface ArgumentAdapter<T> extends ArgumentCompleter {

  @Override
  default @Unmodifiable @NotNull List<String> complete(@NotNull Sender<?> commandSender) {
    return Collections.emptyList();
  }

  @NotNull
  T parse(@NotNull Sender<?> commandSender, @NotNull String rawArgument) throws ArgumentException;
}
