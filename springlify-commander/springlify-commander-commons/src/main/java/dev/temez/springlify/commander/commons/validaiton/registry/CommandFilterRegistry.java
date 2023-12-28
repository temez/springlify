package dev.temez.springlify.commander.commons.validaiton.registry;

import dev.temez.springlify.commander.commons.validaiton.CommandFilter;
import dev.temez.springlify.platform.commons.exception.FormattedException;
import java.lang.annotation.Annotation;
import org.jetbrains.annotations.NotNull;

/**
 * Registry interface for managing and retrieving command filters based on filter annotations.
 *
 * @since 0.5.8.9dev
 */
public interface CommandFilterRegistry {

  /**
   * Retrieves a command filter for a specific filter annotation type.
   *
   * @param filterAnnotation The class representing the filter annotation type.
   * @param <T>              The type of the filter annotation.
   * @return A command filter associated with the given filter annotation type.
   * @throws FormattedException If an exception occurs during the retrieval process.
   */
  @NotNull <T extends Annotation> CommandFilter<T> get(
      @NotNull Class<T> filterAnnotation
  ) throws FormattedException;

  /**
   * Registers a command filter in the registry.
   *
   * @param commandFilter The command filter to be registered.
   * @throws FormattedException If the command filter is already registered in the registry.
   */
  void register(@NotNull CommandFilter<?> commandFilter) throws FormattedException;
}
