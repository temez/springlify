package dev.temez.springlify.commander.command.invocation;

import dev.temez.springlify.commander.command.Command;
import dev.temez.springlify.commander.command.sender.Sender;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Unmodifiable;

import java.util.List;

/**
 * Interface representing a command invocation.
 *
 * @since 0.7.0.0-RC1
 */
public interface CommandInvocation {

  /**
   * Retrieves the sender of the command invocation.
   *
   * @return The sender of the command invocation.
   */
  @NotNull Sender<?> getSender();

  /**
   * Retrieves the command associated with the invocation.
   *
   * @return The command associated with the invocation.
   */
  @NotNull Command getCommand();

  /**
   * Sets the command associated with the invocation.
   *
   * @param command The command to set.
   */
  void setCommand(@NotNull Command command);

  /**
   * Retrieves the arguments passed with the command invocation.
   *
   * @return An unmodifiable list of arguments.
   */
  @NotNull @Unmodifiable List<String> getArguments();

  /**
   * Sets the arguments for the command invocation.
   *
   * @param args The arguments to set.
   */
  void setArguments(@NotNull List<String> args);

  /**
   * Retrieves the index of the last argument.
   *
   * @return The index of the last argument.
   */
  int getLastArgumentIndex();

  /**
   * Retrieves the last argument.
   *
   * @return The last argument.
   * @throws IndexOutOfBoundsException If there are no arguments.
   */
  @NotNull String getLastArgument() throws IndexOutOfBoundsException;
}
