package dev.temez.springlify.commander.commons.sender;

import net.kyori.adventure.text.Component;
import org.jetbrains.annotations.NotNull;

public interface Sender<T> {

  @NotNull T getPlatformSender();

  boolean isConsoleSender();

  boolean hasPermission(@NotNull String permission);

  void sendMessage(@NotNull Component component);
}
