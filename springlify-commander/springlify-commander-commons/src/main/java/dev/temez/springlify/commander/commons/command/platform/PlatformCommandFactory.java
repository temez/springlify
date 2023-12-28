package dev.temez.springlify.commander.commons.command.platform;

import dev.temez.springlify.commander.commons.command.RegisteredCommand;
import org.jetbrains.annotations.NotNull;

public interface PlatformCommandFactory {

  @NotNull PlatformCommand create(@NotNull RegisteredCommand registeredCommand);
}
