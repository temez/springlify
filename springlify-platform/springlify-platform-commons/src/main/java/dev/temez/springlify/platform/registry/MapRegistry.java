package dev.temez.springlify.platform.registry;

import dev.temez.springlify.platform.exception.RegistryException;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.jetbrains.annotations.Unmodifiable;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface MapRegistry<T extends RegistryEntry<I>, I> {

  @NotNull Map<I, T> getRegistry();

  default @NotNull T get(@NotNull I id) throws RegistryException {
    return getOrNull(id)
        .orElseThrow(() -> new RegistryException("Entry in %s wih id %s not found".formatted(getClass().getSimpleName(), id)));
  }

  default @NotNull Optional<T> getOrNull(@NotNull I id) {
    return Optional.ofNullable(getRegistry().get(id));
  }

  default @Nullable T next(@NotNull T entry) {
    List<T> registry = toList();
    int index = registry.indexOf(entry);
    if (getRegistry().size() == index) {
      return null;
    }
    return registry.get(index + 1);
  }

  default @NotNull T defaultValue() {
    return new ArrayList<>(getRegistry().values()).get(0);
  }

  default @NotNull @Unmodifiable List<T> toList() {
    return List.copyOf(getRegistry().values());
  }
}
