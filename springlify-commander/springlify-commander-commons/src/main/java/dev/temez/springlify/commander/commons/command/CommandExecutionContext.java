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

@Getter
@Builder
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public final class CommandExecutionContext {


  @NotNull Object commandExecutor;

  @NotNull Method method;

  public @Unmodifiable @NotNull List<Class<?>> getParameterTypes() {
    return Arrays.stream(method.getParameterTypes())
        .skip(1)
        .toList();
  }

  public @Unmodifiable @NotNull List<Parameter> getParameters() {
    return Arrays.stream(method.getParameters())
        .skip(1)
        .toList();
  }

  public Parameter getParameter(int position) {
    return getParameters().get(position);
  }

  public int getParametersCount() {
    return getParameters().size();
  }
}
