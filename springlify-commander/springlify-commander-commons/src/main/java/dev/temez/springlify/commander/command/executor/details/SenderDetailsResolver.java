package dev.temez.springlify.commander.command.executor.details;

import dev.temez.springlify.commander.command.sender.Sender;
import org.jetbrains.annotations.NotNull;

import java.lang.reflect.Method;

public interface SenderDetailsResolver {

  @NotNull Object resolve(@NotNull Method method, @NotNull Sender<?> sender);
}
