package dev.temez.springlify.commander.command.executor.provider;

import dev.temez.springlify.commander.command.sender.Sender;
import org.jetbrains.annotations.NotNull;

import java.lang.reflect.Parameter;

public interface ArgumentProvider {

  @NotNull Object parse(@NotNull Sender<?> sender, @NotNull String rawArgument, @NotNull Parameter parameter);

  boolean supports(@NotNull Parameter parameter);

}
