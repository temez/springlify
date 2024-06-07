package dev.temez.springlify.platform.configuration.mapper;

import dev.temez.springlify.platform.configuration.mapper.schema.MappingSchema;
import dev.temez.springlify.platform.exception.MappingException;
import org.jetbrains.annotations.NotNull;

import java.util.Collection;

/**
 * Interface for mapping configurations between different data structures or formats.
 *
 * <p>This interface defines methods for mapping configurations from source objects to result objects,
 * as well as retrieving mapping schemas for a given source and result class.</p>
 *
 * @see MappingSchema
 * @see MappingException
 * @since 0.7.0.0-RC1
 */
public interface ConfigurationMapper {

  /**
   * Maps a source object to a result object of the specified class.
   *
   * @param <S>         the type of the source object
   * @param <R>         the type of the result object
   * @param source      the source object to map
   * @param resultClass the class of the result object
   * @return the mapped result object
   * @throws MappingException if schema was not resolved
   */
  @NotNull <S, R> R map(@NotNull S source, @NotNull Class<R> resultClass) throws MappingException;

  /**
   * Maps a collection of source objects to a collection of result objects of the specified class.
   *
   * @param <S>         the type of the source objects
   * @param <R>         the type of the result objects
   * @param source      the collection of source objects to map
   * @param resultClass the class of the result objects
   * @return the collection of mapped result objects
   * @throws MappingException if schema was not resolved
   */
  @NotNull <S, R> Collection<R> map(@NotNull Collection<S> source, @NotNull Class<R> resultClass) throws MappingException;


}

