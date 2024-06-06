package dev.temez.springlify.commander.command.filter.resolver;

import dev.temez.springlify.commander.command.filter.CommandFilter;
import dev.temez.springlify.commander.exception.filter.CommandFilterNotFoundException;
import org.jetbrains.annotations.NotNull;

import java.lang.annotation.Annotation;

public interface CommandFilterResolver {

  @NotNull <A extends Annotation> CommandFilter<A> getFilter(@NotNull Class<A> annotation) throws CommandFilterNotFoundException;

  @NotNull <A extends Annotation> CommandFilter<A> getFilter(@NotNull A annotation) throws CommandFilterNotFoundException;

}
