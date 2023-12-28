package dev.temez.springlify.commander.commons.validaiton;

import dev.temez.springlify.commander.commons.exception.ValidationException;
import dev.temez.springlify.commander.commons.sender.Sender;
import java.lang.annotation.Annotation;
import org.jetbrains.annotations.NotNull;

/**
 * An interface for defining command filters based on a specific annotation type.
 *
 * @param <A> The type of annotation used by the filter.
 */
public interface CommandFilter<A extends Annotation> {

  /**
   * Gets the type of annotation associated with this filter.
   *
   * @return The class object representing the annotation type.
   */
  @NotNull Class<A> getFilterAnnotationType();

  /**
   * Applies the filter to the provided sender based on the given annotation.
   *
   * @param sender     The sender of the command.
   * @param annotation The annotation to apply for filtering.
   * @throws ValidationException If the filtering process fails due to validation issues.
   */
  void filter(@NotNull Sender<?> sender, @NotNull A annotation) throws ValidationException;

}
