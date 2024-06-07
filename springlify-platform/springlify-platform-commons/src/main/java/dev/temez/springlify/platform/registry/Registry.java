package dev.temez.springlify.platform.registry;

import dev.temez.springlify.platform.exception.RegistryException;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.Optional;

/**
 * Interface representing a registry of entries.
 *
 * <p>This interface provides methods for accessing and manipulating a collection of registry entries.</p>
 *
 * @param <T> the type of the registry entry
 * @param <I> the type of the identifier
 * @since 0.7.0.0-RC1
 */
public interface Registry<T extends RegistryEntry<I>, I> {

  /**
   * Retrieves an unmodifiable list of entries in the registry.
   *
   * @return the unmodifiable list of entries
   */
  @NotNull
  List<T> getRegistry();

  /**
   * Retrieves the entry with the specified identifier from the registry.
   *
   * @param id the identifier of the entry to retrieve
   * @return the entry with the specified identifier
   * @throws RegistryException if the entry with the specified identifier is not found
   */
  default @NotNull T get(@NotNull I id) throws RegistryException {
    return Optional.ofNullable(getOrNull(id))
        .orElseThrow(() -> new RegistryException("Entry in %s with id %s not found".formatted(getClass().getSimpleName(), id)));
  }

  /**
   * Retrieves the entry with the specified identifier from the registry, or null if not found.
   *
   * @param id the identifier of the entry to retrieve
   * @return the entry with the specified identifier, or null if not found
   */
  default @Nullable T getOrNull(@NotNull I id) {
    return getRegistry()
        .stream().filter(entry -> entry.getId().equals(id))
        .findFirst()
        .orElse(null);
  }

  /**
   * Retrieves the next entry in the registry after the specified entry.
   *
   * @param entry the current entry
   * @return the next entry after the specified entry, or null if the specified entry is the last one
   */
  default @Nullable T next(@NotNull T entry) {
    int index = getRegistry().indexOf(entry);
    if (index == -1 || index == getRegistry().size() - 1) {
      return null;
    }
    return getRegistry().get(index + 1);
  }

  /**
   * Retrieves the default entry in the registry.
   *
   * @return the default entry
   */
  default @NotNull T defaultValue() {
    return getRegistry().get(0);
  }
}
