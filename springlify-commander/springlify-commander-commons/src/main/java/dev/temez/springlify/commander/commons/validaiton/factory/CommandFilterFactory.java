package dev.temez.springlify.commander.commons.validaiton.factory;

import dev.temez.springlify.commander.commons.validaiton.simple.SimpleCommandFilter;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.List;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Unmodifiable;

/**
 * Factory interface for obtaining filter annotations and simple command filters
 * associated with a command method.
 *
 * @since 0.5.8.9dev
 */
public interface CommandFilterFactory {

  /**
   * Retrieves the filter annotations associated with the given command method.
   *
   * @param commandMethod The command method for which filter annotations are retrieved.
   * @return A list of filter annotations.
   */
  @NotNull List<Annotation> getFilterAnnotations(@NotNull Method commandMethod);

  /**
   * Retrieves the simple command filters associated with the given command method.
   *
   * @param commandMethod The command method for which simple command filters are retrieved.
   * @return A list of simple command filters.
   */
  @Unmodifiable
  @NotNull List<? extends SimpleCommandFilter> getSimpleFilters(@NotNull Method commandMethod);

}
