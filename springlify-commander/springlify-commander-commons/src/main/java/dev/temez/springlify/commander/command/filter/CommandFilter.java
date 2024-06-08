package dev.temez.springlify.commander.command.filter;

import dev.temez.springlify.commander.command.sender.Sender;
import dev.temez.springlify.commander.exception.filter.CommandFilterException;
import org.jetbrains.annotations.NotNull;

import java.lang.annotation.Annotation;

/**
 * Represents a command filter that operates based on an annotation type.
 *
 * @param <A> The type of annotation associated with the filter.
 * @since 0.7.0.0-RC1
 */
public interface CommandFilter<A extends Annotation> {

  /**
   * Performs filtering based on the provided annotation.
   *
   * @param sender     The sender executing the command.
   * @param annotation The annotation associated with the filter.
   * @throws CommandFilterException If an error occurs during the filtering process.
   */
  void doFilter(@NotNull Sender<?> sender, @NotNull A annotation) throws CommandFilterException;

}
