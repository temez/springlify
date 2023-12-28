package dev.temez.springlify.commander.commons.adapter;

import dev.temez.springlify.commander.commons.exception.FormattedException;
import java.util.HashMap;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Component;

@Component
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public final class ArgumentAdapterFactoryImpl implements ArgumentAdapterFactory {

  @NotNull
  HashMap<Class<?>, ArgumentAdapter<?>> registeredAdapters = new HashMap<>();

  @Override
  @SuppressWarnings("unchecked")
  public @NotNull <T> ArgumentAdapter<T> get(@NotNull Class<T> classToAdapt)
      throws RuntimeException {
    if (!registeredAdapters.containsKey(classToAdapt)) {
      throw new FormattedException(
          "Could not find an adapter for %s",
          classToAdapt.getSimpleName()
      );
    }
    return (ArgumentAdapter<T>) registeredAdapters.get(classToAdapt);
  }

  @Override
  public void register(@NotNull ArgumentAdapter<?> argumentAdapter) {
    registeredAdapters.put(argumentAdapter.getTargetClass(), argumentAdapter);
  }
}
