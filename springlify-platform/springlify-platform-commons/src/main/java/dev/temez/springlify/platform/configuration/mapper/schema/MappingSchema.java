package dev.temez.springlify.platform.configuration.mapper.schema;

import org.jetbrains.annotations.NotNull;

import java.util.Collection;

/**
 * Interface representing a mapping schema for mapping between source and result objects.
 *
 * <p>This interface defines a method for mapping a single source object to a result object,
 * as well as a default method for mapping a collection of source objects to a collection of result objects.</p>
 *
 * @param <S> the type of the source objects
 * @param <R> the type of the result objects
 * @since 0.7.0.0-RC1
 */
public interface MappingSchema<S, R> {

  /**
   * Maps a source object to a result object.
   *
   * @param source the source object to map
   * @return the mapped result object
   */
  @NotNull
  R map(@NotNull S source);

  /**
   * Maps a collection of source objects to a collection of result objects.
   *
   * @param sourceList the collection of source objects to map
   * @return the collection of mapped result objects
   */
  default @NotNull Collection<R> map(@NotNull Collection<S> sourceList) {
    return sourceList.stream()
        .map(this::map)
        .toList();
  }
}
