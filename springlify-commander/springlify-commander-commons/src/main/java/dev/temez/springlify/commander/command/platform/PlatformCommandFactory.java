package dev.temez.springlify.commander.command.platform;

import dev.temez.springlify.commander.command.Command;
import org.jetbrains.annotations.NotNull;


public interface PlatformCommandFactory {

  @NotNull
  PlatformCommand create(@NotNull Command registeredCommand);
}
