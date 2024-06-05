package dev.temez.springlify.commander.command.metadata;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.jetbrains.annotations.Unmodifiable;

import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.List;

public interface CommandInvocationMetadata {

  @NotNull Object getExecutor();

  @Nullable Method getCommandMethod();

  @NotNull @Unmodifiable List<Parameter> getParameters() throws UnsupportedOperationException;

  @NotNull Parameter getParameter(int index) throws IllegalArgumentException, UnsupportedOperationException;

  int getParameterCount() throws UnsupportedOperationException;
}
