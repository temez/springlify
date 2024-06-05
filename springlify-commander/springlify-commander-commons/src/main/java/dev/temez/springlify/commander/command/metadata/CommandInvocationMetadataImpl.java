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

  public CommandInvocationMetadataImpl(@NotNull Object executor, @Nullable Method commandMethod) {
    this.executor = executor;
    this.commandMethod = commandMethod;
    this.parameters = commandMethod == null ? null : Arrays.stream(commandMethod.getParameters()).skip(1).toList();
  }

  public @NotNull List<Parameter> getParameters() throws UnsupportedOperationException {
    if (parameters == null) {
      throw new UnsupportedOperationException("Command method is null. Parameters were not discovered.");
    }
    return parameters;
  }

  public @NotNull Parameter getParameter(int index) throws IllegalArgumentException, UnsupportedOperationException {
    if (parameters == null) {
      throw new UnsupportedOperationException("Command method is null. Parameters were not discovered.");
    }
    if (index < 0 || index >= parameters.size()) {
      throw new IllegalArgumentException("Invalid parameter index: %d".formatted(index));
    }
    return parameters.get(index);
  }

  public int getParameterCount() throws UnsupportedOperationException {
    if (parameters == null) {
      throw new UnsupportedOperationException("Command method is null. Parameters were not discovered.");
    }
    return parameters.size();
  }
}
