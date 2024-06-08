package dev.temez.springlify.commander.command.metadata;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.Arrays;
import java.util.List;

/**
 * Implementation of {@link CommandInvocationMetadata}.
 *
 * @since 0.7.0.0-RC1
 */
@Getter
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class CommandInvocationMetadataImpl implements CommandInvocationMetadata {

  @NotNull
  Object executor;

  @Nullable
  Method commandMethod;

  @Nullable
  List<Parameter> parameters;

  /**
   * Constructs a CommandInvocationMetadataImpl with the specified executor and command method.
   * It also initializes the parameters by extracting them from the command method if available.
   *
   * @param executor      The executor of the command.
   * @param commandMethod The method associated with the command.
   */
  public CommandInvocationMetadataImpl(@NotNull Object executor, @Nullable Method commandMethod) {
    this.executor = executor;
    this.commandMethod = commandMethod;
    this.parameters = commandMethod == null ? null : Arrays.asList(commandMethod.getParameters()).subList(1, commandMethod.getParameters().length);
  }

  /**
   * Retrieves the parameters associated with the command method.
   *
   * @return The parameters associated with the command method.
   * @throws UnsupportedOperationException If parameters were not discovered.
   */
  @Override
  public @NotNull List<Parameter> getParameters() throws UnsupportedOperationException {
    if (parameters == null) {
      throw new UnsupportedOperationException("Command method is null. Parameters were not discovered.");
    }
    return parameters;
  }

  /**
   * Retrieves the parameter at the specified index.
   *
   * @param index The index of the parameter.
   * @return The parameter at the specified index.
   * @throws IllegalArgumentException      If the index is invalid.
   * @throws UnsupportedOperationException If parameters were not discovered.
   */
  @Override
  public @NotNull Parameter getParameter(int index) throws IllegalArgumentException, UnsupportedOperationException {
    if (parameters == null) {
      throw new UnsupportedOperationException("Command method is null. Parameters were not discovered.");
    }
    if (index < 0 || index >= parameters.size()) {
      throw new IllegalArgumentException("Invalid parameter index: %d".formatted(index));
    }
    return parameters.get(index);
  }

  /**
   * Retrieves the count of parameters associated with the command method.
   *
   * @return The count of parameters.
   * @throws UnsupportedOperationException If parameters were not discovered.
   */
  @Override
  public int getParameterCount() throws UnsupportedOperationException {
    if (parameters == null) {
      throw new UnsupportedOperationException("Command method is null. Parameters were not discovered.");
    }
    return parameters.size();
  }
}
