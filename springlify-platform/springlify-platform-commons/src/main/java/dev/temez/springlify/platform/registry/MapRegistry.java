package dev.temez.springlify.platform.registry;

import dev.temez.springlify.platform.exception.RegistryException;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * Interface representing a registry of entries stored in a map.
 *
 * <p>This interface extends {@link Registry} and provides methods for accessing entries
 * stored in a map with identifiers.</p>
 *
 * @param <T> the type of the registry entry
 * @param <I> the type of the identifier
 * @see Registry
 * @since 0.7.0.0-RC1
 */
public interface MapRegistry<T extends RegistryEntry<I>, I> {

  /**
   * Retrieves the registry entries stored in a map.
   *
   * @return the map of registry entries
   */
  @NotNull
  Map<I, T> getRegistry();

  /**
   * Retrieves the entry with the specified identifier from the registry.
   *
   * @param id the identifier of the entry to retrieve
   * @return the entry with the specified identifier
   * @throws RegistryException if the entry with the specified identifier is not found
   */
  default @NotNull T get(@NotNull I id) throws RegistryException {
    return getOptional(id)
        .orElseThrow(() -> new RegistryException("Entry in %s with id %s not found".formatted(getClass().getSimpleName(), id)));
  }

  /**
   * Retrieves the entry with the specified identifier from the registry, or empty if not found.
   *
   * @param id the identifier of the entry to retrieve
   * @return an optional containing the entry with the specified identifier, or empty if not found
   */
  default @NotNull Optional<T> getOptional(@NotNull I id) {
    return Optional.ofNullable(getRegistry().get(id));
  }

  /**
   * Retrieves the next entry in the registry after the specified entry.
   *
   * @param entry the current entry
   * @return the next entry after the specified entry, or null if the specified entry is the last one
   */
  default @NotNull Optional<T> next(@NotNull T entry) {
    List<T> registry = new ArrayList<>(getRegistry().values());
    int index = registry.indexOf(entry);
    if (index == -1 || index == getRegistry().size() - 1) {
      return Optional.empty();
    }
    return Optional.of(registry.get(index + 1));
  }

  /**
   * Retrieves the default entry in the registry.
   *
   * @return the default entry
   */
  default @NotNull T defaultValue() {
    return new ArrayList<>(getRegistry().values()).get(0);
  }

  /**
   * Converts the registry entries stored in the map to an unmodifiable list.
   *
   * @return the unmodifiable list of registry entries
   */
  default @NotNull List<T> toList() {
    return List.copyOf(getRegistry().values());
  }
}

