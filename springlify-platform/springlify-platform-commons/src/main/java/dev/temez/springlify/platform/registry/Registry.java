package dev.temez.springlify.platform.registry;

import dev.temez.springlify.platform.exception.RegistryException;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.jetbrains.annotations.Unmodifiable;

import java.util.List;
import java.util.Optional;

public interface Registry<T extends RegistryEntry<I>, I> {

  @NotNull @Unmodifiable List<T> getRegistry();

  default @NotNull T get(@NotNull I id) throws RegistryException {
    return Optional.ofNullable(getOrNull(id))
        .orElseThrow(() -> new RegistryException("Entry in %s wih id %s not found".formatted(getClass().getSimpleName(), id)));
  }

  default @Nullable T getOrNull(@NotNull I id) {
    return getRegistry()
        .stream().filter(j -> j.getId().equals(id))
        .findFirst()
        .orElse(null);
  }

  default @Nullable T next(@NotNull T entry) {
    int index = getRegistry().indexOf(entry);
    if (getRegistry().size() == index) {
      return null;
    }
    return getRegistry().get(index + 1);
  }

  default @NotNull T defaultValue() {
    return getRegistry().get(0);
  }
}