package dev.temez.springlify.commander.command.filter.factory;

import dev.temez.springlify.commander.command.filter.CommandFilter;
import dev.temez.springlify.commander.exception.CommandFilterException;
import org.jetbrains.annotations.NotNull;

import java.lang.annotation.Annotation;

public interface CommandFilterFactory {

  @NotNull <T extends Annotation> CommandFilter<T> getFilter(@NotNull Class<T> filterAnnotation) throws CommandFilterException;
}
