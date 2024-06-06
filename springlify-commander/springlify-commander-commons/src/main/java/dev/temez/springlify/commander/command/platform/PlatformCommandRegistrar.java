package dev.temez.springlify.commander.command.platform;

import org.jetbrains.annotations.NotNull;

public interface PlatformCommandRegistrar {

  void register(@NotNull PlatformCommand platformCommand);
}
