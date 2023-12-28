package dev.temez.springlify.commander.commons.command.platform;

import org.jetbrains.annotations.NotNull;

public interface PlatformCommand {

  void register(Object @NotNull ... args);
}
