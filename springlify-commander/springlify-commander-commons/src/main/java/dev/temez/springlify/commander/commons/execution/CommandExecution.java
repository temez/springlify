package dev.temez.springlify.commander.commons.execution;

import dev.temez.springlify.commander.commons.command.RegisteredCommand;
import dev.temez.springlify.commander.commons.sender.Sender;
import java.util.List;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldDefaults;
import lombok.experimental.NonFinal;
import org.jetbrains.annotations.NotNull;

@Getter
@Builder
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public final class CommandExecution {


  @NotNull Sender<?> commandSender;

  @Setter
  @NonFinal
  @NotNull List<String> arguments;

  @NotNull
  @Setter
  @NonFinal
  RegisteredCommand command;

  public int getLastArgumentIndex() {
    return arguments.isEmpty() ? 0 : arguments.size() - 1;
  }

  public String getLastArgument() {
    return arguments.get(getLastArgumentIndex());
  }
}
