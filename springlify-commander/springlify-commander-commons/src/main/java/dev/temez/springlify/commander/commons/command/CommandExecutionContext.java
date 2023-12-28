package dev.temez.springlify.commander.commons.command;

import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.Arrays;
import java.util.List;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Unmodifiable;


/**
 * Represents the execution context of a Commander plugin command.
 *
 * @since 0.5.8.9dev
 */
@Getter
@Builder
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public final class CommandExecutionContext {

  @NotNull
  Object commandExecutor;

  @NotNull
  Method method;

  /**
   * Gets the parameters of the command method.
   *
   * @return The parameters of the command method.
   */
  public @Unmodifiable @NotNull List<Parameter> getParameters() {
    return Arrays.stream(method.getParameters())
        .skip(1)
        .toList();
  }

  /**
   * Gets a specific parameter by position.
   *
   * @param position The position of the parameter.
   * @return The specified parameter.
   */
  public Parameter getParameter(int position) {
    return getParameters().get(position);
  }

  /**
   * Gets the count of parameters in the command method.
   *
   * @return The count of parameters.
   */
  public int getParametersCount() {
    return getParameters().size();
  }
}