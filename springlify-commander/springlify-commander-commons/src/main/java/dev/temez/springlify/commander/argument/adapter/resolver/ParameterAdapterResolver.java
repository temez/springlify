package dev.temez.springlify.commander.argument.adapter.resolver;

import dev.temez.springlify.commander.argument.adapter.ParameterAdapter;
import dev.temez.springlify.commander.exception.argument.ArgumentAdapterException;
import org.jetbrains.annotations.NotNull;


/**
 * An interface for resolving parameter adapters based on the parameter type.
 * <p>
 * Implementations of this interface provide a mechanism for obtaining the appropriate parameter adapter
 * for a given parameter type.
 * </p>
 *
 * @see ParameterAdapter
 * @since 0.7.0.0-RC1
 */
public interface ParameterAdapterResolver {

  /**
   * Retrieves the parameter adapter for the specified parameter type.
   *
   * @param parameterType The class representing the type of parameter for which an adapter is needed.
   * @param <T>           The type of the parameter.
   * @return The parameter adapter for the specified parameter type.
   * @throws ArgumentAdapterException If a parameter adapter cannot be resolved for the specified type.
   */
  @NotNull <T> ParameterAdapter<T> getAdapter(@NotNull Class<T> parameterType) throws ArgumentAdapterException;

}
