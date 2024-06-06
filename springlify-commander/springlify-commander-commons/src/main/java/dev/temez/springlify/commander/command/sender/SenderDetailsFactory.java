package dev.temez.springlify.commander.command.sender;

import org.jetbrains.annotations.NotNull;

public interface SenderDetailsFactory<T> {

  @NotNull T getDetails(@NotNull Sender<?> commandSender);
}
