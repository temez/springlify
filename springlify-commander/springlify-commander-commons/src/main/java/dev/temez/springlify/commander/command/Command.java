package dev.temez.springlify.commander.command;

import dev.temez.springlify.commander.command.metadata.CommandInvocationMetadata;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Unmodifiable;

import java.util.List;

/**
 * Interface representing a command.
 *
 * @since 0.7.0.0-RC1
 */
public interface Command {

  /**
   * Retrieves the parent command of this command.
   *
   * @return The parent command.
   */
  @NotNull
  Command getParentCommand();

  /**
   * Sets the parent command of this command.
   *
   * @param command The parent command to set.
   */
  void setParentCommand(@NotNull Command command);

  /**
   * Retrieves the name of the command.
   *
   * @return The name of the command.
   */
  @NotNull
  String getName();

  /**
   * Retrieves the description of the command.
   *
   * @return The description of the command.
   */
  @NotNull
  String getDescription();

  /**
   * Retrieves the aliases of the command.
   *
   * @return An unmodifiable list of aliases.
   */
  @NotNull
  @Unmodifiable
  List<String> getAlias();

  /**
   * Retrieves the type of the command.
   *
   * @return The type of the command.
   */
  @NotNull
  CommandType getType();

  /**
   * Retrieves the subcommands of the command.
   *
   * @return An unmodifiable list of subcommands.
   */
  @NotNull
  @Unmodifiable
  List<Command> getSubcommands();

  /**
   * Retrieves the metadata associated with the command invocation.
   *
   * @return The metadata associated with the command invocation.
   */
  @NotNull
  CommandInvocationMetadata getCommandInvocationMetadata();

  /**
   * Retrieves the root command of the command hierarchy.
   *
   * @return The root command of the hierarchy.
   */
  @NotNull
  Command getRootCommand();

  /**
   * Retrieves the full name of the command, including its parent commands (if any).
   *
   * @return The full name of the command.
   */
  @NotNull
  String getFullName();
}
