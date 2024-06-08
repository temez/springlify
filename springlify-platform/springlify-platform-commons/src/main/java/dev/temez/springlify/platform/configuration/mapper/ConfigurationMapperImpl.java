package dev.temez.springlify.platform.configuration.mapper;

import dev.temez.springlify.platform.configuration.mapper.resolver.SchemaResolver;
import dev.temez.springlify.platform.configuration.mapper.schema.MappingSchema;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Component;

import java.util.Collection;

/**
 * Implementation of {@link ConfigurationMapper} for mapping configurations using mapping schemas.
 *
 * <p>This component uses a {@link SchemaResolver} to resolve mapping schemas and map configurations
 * between different data structures or formats.</p>
 *
 * @see ConfigurationMapper
 * @see SchemaResolver
 * @since 0.7.0.0-RC1
 */
@Component
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class ConfigurationMapperImpl implements ConfigurationMapper {

  /**
   * The schema resolver used for resolving mapping schemas.
   */
  @NotNull
  SchemaResolver schemaResolver;

  /**
   * Maps a source object to a result object of the specified class.
   *
   * @param <S>         the type of the source object
   * @param <R>         the type of the result object
   * @param source      the source object to map
   * @param resultClass the class of the result object
   * @return the mapped result object
   */
  @Override
  @SuppressWarnings("unchecked")
  public <S, R> @NotNull R map(@NotNull S source, @NotNull Class<R> resultClass) {
    Class<S> sourceClass = (Class<S>) source.getClass();
    MappingSchema<S, R> schema = schemaResolver.getSchema(sourceClass, resultClass);
    return schema.map(source);
  }

  /**
   * Maps a collection of source objects to a collection of result objects of the specified class.
   *
   * <p>This method is not implemented and throws {@link UnsupportedOperationException}.</p>
   *
   * @param <S>         the type of the source objects
   * @param <R>         the type of the result objects
   * @param source      the collection of source objects to map
   * @param resultClass the class of the result objects
   * @return the collection of mapped result objects
   * @throws UnsupportedOperationException always thrown as this method is not implemented
   */
  @Override
  public @NotNull <S, R> Collection<R> map(@NotNull Collection<S> source, @NotNull Class<R> resultClass) {
    throw new UnsupportedOperationException();
  }
}
