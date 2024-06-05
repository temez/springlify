package dev.temez.springlify.starter.server;

import org.jetbrains.annotations.NotNull;

public interface ServerPlatformAdapter {

  void registerEventListener(@NotNull Object listener);

  void unregisterEventListener(@NotNull Object listener);
}
