package dev.temez.springlify.commander.command.platform;

import dev.temez.springlify.commander.command.Command;
import dev.temez.springlify.commander.command.completer.CommandCompleter;
import dev.temez.springlify.commander.command.preprocessor.ExecutionPreprocessor;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public final class BukkitCommandFactory implements PlatformCommandFactory {

  @NotNull
  CommandCompleter commandCompleter;

  @NotNull
  ExecutionPreprocessor executionPreprocessor;

  @Override
  public @NotNull PlatformCommand create(@NotNull Command registeredCommand) {

    return new CommanderBukkitCommand(
        registeredCommand, commandCompleter, executionPreprocessor
    );
  }
}

