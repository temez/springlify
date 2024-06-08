package dev.temez.springlify.platform.registry;

import org.jetbrains.annotations.NotNull;

/**
 * Interface representing a registry entry with an identifier.
 *
 * <p>This interface defines a method for retrieving the identifier of the registry entry.</p>
 *
 * @param <I> the type of the identifier
 * @since 0.7.0.0-RC1
 */
public interface RegistryEntry<I> {

  /**
   * Retrieves the identifier of the registry entry.
   *
   * @return the identifier
   */
  @NotNull
  I getId();
}
