package dev.temez.springlify.commander.command.metadata;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Unmodifiable;

import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.List;

/**
 * Interface representing metadata associated with a command invocation.
 *
 * @since 0.7.0.0-RC1
 */
public interface CommandInvocationMetadata {

  /**
   * Retrieves the executor of the command.
   *
   * @return The executor of the command.
   */
  @NotNull Object getExecutor();

  /**
   * Retrieves the method associated with the command.
   *
   * @return The method associated with the command.
   */
  Method getCommandMethod();

  /**
   * Retrieves the parameters of the command method.
   *
   * @return An unmodifiable list of parameters.
   * @throws UnsupportedOperationException If parameter retrieval is not supported.
   */
  @NotNull @Unmodifiable List<Parameter> getParameters() throws UnsupportedOperationException;

  /**
   * Retrieves the parameter at the specified index.
   *
   * @param index The index of the parameter.
   * @return The parameter at the specified index.
   * @throws IllegalArgumentException      If the index is invalid.
   * @throws UnsupportedOperationException If parameter retrieval is not supported.
   */
  @NotNull Parameter getParameter(int index) throws IllegalArgumentException, UnsupportedOperationException;

  /**
   * Retrieves the count of parameters associated with the command method.
   *
   * @return The count of parameters.
   * @throws UnsupportedOperationException If parameter count retrieval is not supported.
   */
  int getParameterCount() throws UnsupportedOperationException;
}
