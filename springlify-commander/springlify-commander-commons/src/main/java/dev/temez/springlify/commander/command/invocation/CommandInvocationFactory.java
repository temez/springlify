package dev.temez.springlify.commander.command.invocation;

import dev.temez.springlify.commander.command.Command;
import org.jetbrains.annotations.NotNull;

/**
 * Factory interface for creating command invocations.
 *
 * @since 0.7.0.0-RC1
 */
public interface CommandInvocationFactory {

  /**
   * Creates a command invocation with the specified registered command and additional objects.
   *
   * @param registeredCommand The registered command for the invocation.
   * @param objects           Additional objects associated with the invocation.
   * @return A command invocation.
   */
  @NotNull CommandInvocation create(@NotNull Command registeredCommand, Object @NotNull ... objects);
}
