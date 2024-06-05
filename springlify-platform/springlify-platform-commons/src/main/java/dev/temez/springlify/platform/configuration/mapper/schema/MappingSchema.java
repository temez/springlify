package dev.temez.springlify.platform.configuration.mapper.schema;

import org.jetbrains.annotations.NotNull;

import java.util.Collection;

public interface MappingSchema<S, R> {

  @NotNull R map(@NotNull S source);

  default @NotNull Collection<R> map(@NotNull Collection<S> sourceList) {
    return sourceList.stream()
        .map(this::map)
        .toList();
  }
}
