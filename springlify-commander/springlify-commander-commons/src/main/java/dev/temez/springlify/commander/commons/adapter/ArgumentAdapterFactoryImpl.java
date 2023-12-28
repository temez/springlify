package dev.temez.springlify.commander.commons.adapter;

import dev.temez.springlify.platform.commons.exception.FormattedException;
import java.util.HashMap;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Component;

/**
 * A Spring component implementing the ArgumentAdapterFactory interface.
 * This implementation maintains a registry of ArgumentAdapters for different types.
 *
 * @since 0.5.8.9dev
 */
@Component
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public final class ArgumentAdapterFactoryImpl implements ArgumentAdapterFactory {

  @NotNull
  HashMap<Class<?>, ArgumentAdapter<?>> registeredAdapters = new HashMap<>();

  /**
   * {@inheritDoc}
   */
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

  /**
   * {@inheritDoc}
   */
  @Override
  public void register(@NotNull ArgumentAdapter<?> argumentAdapter) {
    registeredAdapters.put(argumentAdapter.getTargetClass(), argumentAdapter);
  }
}
