package dev.temez.springlify.platform.configuration.mapper;

import dev.temez.springlify.platform.configuration.mapper.schema.MappingSchema;
import dev.temez.springlify.platform.exception.MappingException;
import org.jetbrains.annotations.NotNull;

import java.util.Collection;

public interface ConfigurationMapper {

  @NotNull <S, R> R map(@NotNull S source, @NotNull Class<R> resultClass);

  @NotNull <S, R> Collection<R> map(@NotNull Collection<S> source, @NotNull Class<R> resultClass);

  @NotNull <S, R> MappingSchema<S, R> getSchema(
      @NotNull Class<S> sourceClass,
      @NotNull Class<R> resultClass
  ) throws MappingException;
}
