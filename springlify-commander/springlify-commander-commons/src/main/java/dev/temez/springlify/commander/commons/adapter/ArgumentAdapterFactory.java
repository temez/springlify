package dev.temez.springlify.commander.commons.adapter;

import dev.temez.springlify.platform.commons.exception.FormattedException;
import org.jetbrains.annotations.NotNull;

/**
 * This interface represents an ArgumentAdapterFactory, responsible for providing ArgumentAdapters
 * and registering new ArgumentAdapters.
 *
 * @since 0.5.8.9dev
 */
public interface ArgumentAdapterFactory {

  /**
   * Gets an ArgumentAdapter for the specified class.
   *
   * @param classToAdapt The class to adapt.
   * @param <T>          The type of the class to adapt.
   * @return An ArgumentAdapter for the specified class.
   * @throws FormattedException If there is an issue getting the ArgumentAdapter.
   */
  @NotNull <T> ArgumentAdapter<T> get(@NotNull Class<T> classToAdapt) throws FormattedException;

  /**
   * Registers a new ArgumentAdapter.
   *
   * @param argumentAdapter The ArgumentAdapter to register.
   */
  void register(@NotNull ArgumentAdapter<?> argumentAdapter);

}
