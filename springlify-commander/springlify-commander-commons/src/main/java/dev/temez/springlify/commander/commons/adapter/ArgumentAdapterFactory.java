package dev.temez.springlify.commander.commons.adapter;

import dev.temez.springlify.commander.commons.exception.FormattedException;
import org.jetbrains.annotations.NotNull;

public interface ArgumentAdapterFactory {

  @NotNull <T> ArgumentAdapter<T> get(
      @NotNull Class<T> classToAdapt
  ) throws FormattedException;

  void register(@NotNull ArgumentAdapter<?> argumentAdapter);

}
