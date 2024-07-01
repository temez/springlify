package dev.temez.springlify.platform.registry;

import dev.temez.springlify.platform.exception.RegistryException;
import org.jetbrains.annotations.NotNull;

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
    return getOptional(id)
        .orElseThrow(() -> new RegistryException("Entry in %s with id %s not found".formatted(getClass().getSimpleName(), id)));
  }

  /**
   * Retrieves the entry with the specified identifier from the registry, or null if not found.
   *
   * @param id the identifier of the entry to retrieve
   * @return the entry optional with the specified identifier
   */
  default @NotNull Optional<T> getOptional(@NotNull I id) {
    return getRegistry()
        .stream().filter(entry -> entry.getId().equals(id))
        .findFirst();
  }

  /**
   * Retrieves the next entry in the registry after the specified entry.
   *
   * @param entry the current entry
   * @return the next entry optional after the specified entry.
   */
  default @NotNull Optional<T> next(@NotNull T entry) {
    int index = getRegistry().indexOf(entry);
    if (index == -1 || index == getRegistry().size() - 1) {
      return Optional.empty();
    }
    return Optional.of(getRegistry().get(index + 1));
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
