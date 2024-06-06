package dev.temez.springlify.commander.command.filter;

import dev.temez.springlify.commander.command.sender.Sender;
import dev.temez.springlify.commander.exception.filter.CommandFilterException;
import org.jetbrains.annotations.NotNull;

import java.lang.annotation.Annotation;


public interface CommandFilter<A extends Annotation> {

  void doFilter(@NotNull Sender<?> sender, @NotNull A annotation) throws CommandFilterException;

}
