package dev.temez.springlify.commander.commons.completer;

import dev.temez.springlify.commander.commons.sender.Sender;
import java.util.List;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Unmodifiable;

public interface ArgumentCompleter {

  @Unmodifiable
  @NotNull List<String> complete(@NotNull Sender<?> commandSender);

}

