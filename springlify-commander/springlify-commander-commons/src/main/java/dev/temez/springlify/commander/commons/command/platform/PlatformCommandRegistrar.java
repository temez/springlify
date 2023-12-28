package dev.temez.springlify.commander.commons.command.platform;

import org.jetbrains.annotations.NotNull;

public interface PlatformCommandRegistrar {

  void register(@NotNull PlatformCommand platformCommand);
}
