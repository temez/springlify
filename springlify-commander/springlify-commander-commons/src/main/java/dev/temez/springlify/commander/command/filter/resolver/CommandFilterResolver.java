package dev.temez.springlify.commander.command.filter.resolver;

import dev.temez.springlify.commander.command.filter.CommandFilter;
import dev.temez.springlify.commander.exception.filter.CommandFilterNotFoundException;
import org.jetbrains.annotations.NotNull;

import java.lang.annotation.Annotation;

/**
 * Resolves command filters based on annotations.
 *
 * @see CommandFilter
 * @since 0.7.0.0-RC1
 */
public interface CommandFilterResolver {

  /**
   * Retrieves a command filter based on its annotation class.
   *
   * @param <A>        The type of the annotation.
   * @param annotation The annotation class.
   * @return The command filter corresponding to the annotation class.
   * @throws CommandFilterNotFoundException If no command filter is found for the given annotation class.
   */
  @NotNull <A extends Annotation> CommandFilter<A> getFilter(@NotNull Class<A> annotation) throws CommandFilterNotFoundException;

  /**
   * Retrieves a command filter based on its annotation instance.
   *
   * @param <A>        The type of the annotation.
   * @param annotation The annotation instance.
   * @return The command filter corresponding to the annotation instance.
   * @throws CommandFilterNotFoundException If no command filter is found for the given annotation.
   */
  @NotNull <A extends Annotation> CommandFilter<A> getFilter(@NotNull A annotation) throws CommandFilterNotFoundException;
}
