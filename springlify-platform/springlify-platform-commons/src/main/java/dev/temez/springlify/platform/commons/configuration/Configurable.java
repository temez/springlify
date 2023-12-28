package dev.temez.springlify.platform.commons.configuration;

import org.jetbrains.annotations.NotNull;


/**
 * The {@code PlatformConfigurable} interface defines a contract for components that can
 * be configured with platform-specific objects. Implementations of this interface allow
 * integration with different platforms by providing a way to obtain platform-specific objects.
 *
 * <p>Components implementing this interface can expose platform-specific functionality or behavior
 * by allowing access to the underlying platform object.</p>
 *
 * @param <T> the type of the platform-specific object
 * @since 0.5.9.7dev
 */
public interface Configurable<T> {

  /**
   * Gets the platform-specific object associated with this configuration.
   *
   * @return the platform-specific object
   */
  @NotNull T getPlatformObject(Object @NotNull ... params);
}