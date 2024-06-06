package dev.temez.springlify.commander.command.platform;

import org.jetbrains.annotations.NotNull;

public interface PlatformCommand {

  void register(Object @NotNull ... args);
}
