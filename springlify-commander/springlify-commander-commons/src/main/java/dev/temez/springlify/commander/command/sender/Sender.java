package dev.temez.springlify.commander.command.sender;


import org.jetbrains.annotations.NotNull;

import java.util.UUID;

public interface Sender<T> {

  @NotNull T getPlatformSender();

  @NotNull UUID getUuid() throws UnsupportedOperationException;

  boolean isConsoleSender();

  boolean hasPermission(@NotNull String permission);

}