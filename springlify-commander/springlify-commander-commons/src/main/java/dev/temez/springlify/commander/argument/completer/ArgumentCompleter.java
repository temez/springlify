package dev.temez.springlify.commander.argument.completer;

import dev.temez.springlify.commander.command.sender.Sender;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Unmodifiable;

import java.util.List;

public interface ArgumentCompleter {


  @Unmodifiable
  @NotNull List<String> complete(@NotNull Sender<?> commandSender);

}
