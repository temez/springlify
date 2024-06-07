package dev.temez.springlify.platform.configuration.mapper.resolver;

import dev.temez.springlify.platform.configuration.mapper.schema.MappingSchema;
import dev.temez.springlify.platform.exception.MappingException;
import org.jetbrains.annotations.NotNull;

/**
 * Interface for resolving mapping schemas between source and result classes.
 *
 * <p>This interface defines a method for retrieving the mapping schema for a given pair of source and result classes.</p>
 *
 * @see MappingSchema
 * @see MappingException
 * @since 0.7.0.0-RC1
 */
public interface SchemaResolver {

  /**
   * Retrieves the mapping schema for mapping between the specified source and result classes.
   *
   * @param <S>         the type of the source objects
   * @param <R>         the type of the result objects
   * @param sourceClass the class of the source objects
   * @param resultClass the class of the result objects
   * @return the mapping schema for the specified source and result classes
   * @throws MappingException if there is an error retrieving the mapping schema
   */
  @NotNull <S, R> MappingSchema<S, R> getSchema(@NotNull Class<S> sourceClass, @NotNull Class<R> resultClass) throws MappingException;
}
