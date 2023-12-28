package dev.temez.springlify.commander.bukkit.command;

import dev.temez.springlify.commander.bukkit.execution.BukkitExecutionFactory;
import dev.temez.springlify.commander.bukkit.sender.BukkitCommandSender;
import dev.temez.springlify.commander.commons.command.RegisteredCommand;
import dev.temez.springlify.commander.commons.command.platform.PlatformCommand;
import dev.temez.springlify.commander.commons.completer.CommandCompleter;
import dev.temez.springlify.commander.commons.exception.ConformableException;
import dev.temez.springlify.commander.commons.exception.handler.CommanderExceptionHandler;
import dev.temez.springlify.commander.commons.execution.CommandExecution;
import dev.temez.springlify.commander.commons.execution.CommandExecutionFactory;
import dev.temez.springlify.commander.commons.executor.CommandExecutor;
import dev.temez.springlify.commander.commons.validaiton.CommandFilterService;
import java.util.Arrays;
import java.util.List;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.experimental.FieldDefaults;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * Implementation of {@link PlatformCommand} for the Bukkit platform.
 *
 * <p>This class represents a Bukkit command that implements the
 * {@link PlatformCommand} interface.</p>
 *
 * @since 0.5.8.9dev
 */
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class CommanderBukkitCommand extends Command implements PlatformCommand {

  @NotNull CommandExecutionFactory executionFactory = new BukkitExecutionFactory();

  @NotNull RegisteredCommand command;

  @NotNull CommandFilterService commandFilterService;

  @NotNull CommandCompleter commandCompleter;

  @NotNull CommandExecutor commandExecutor;

  @NotNull CommanderExceptionHandler exceptionHandler;

  /**
   * Constructs a new instance of {@code CommanderBukkitCommand} using the builder pattern.
   *
   * @param command              The registered command associated with this Bukkit command.
   * @param commandFilterService The command filter service for handling command
   *                             accessibility and validation.
   * @param commandCompleter     The command completer for handling tab completions.
   * @param commandExecutor      The command executor for executing command logic.
   * @param exceptionHandler     The exception handler for handling exceptions during
   *                             command execution.
   */
  @Builder
  protected CommanderBukkitCommand(
      @NotNull RegisteredCommand command,
      @NotNull CommandFilterService commandFilterService,
      @NotNull CommandCompleter commandCompleter,
      @NotNull CommandExecutor commandExecutor,
      @NotNull CommanderExceptionHandler exceptionHandler
  ) {
    super(command.getName());
    setAliases(command.getAlias());
    this.command = command;
    this.commandFilterService = commandFilterService;
    this.commandCompleter = commandCompleter;
    this.commandExecutor = commandExecutor;
    this.exceptionHandler = exceptionHandler;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public boolean execute(@NotNull CommandSender sender, @NotNull String commandLabel,
                         @NotNull String[] args) {
    CommandExecution execution = executionFactory.create(
        command,
        sender,
        Arrays.stream(args).toList()
    );
    try {
      commandFilterService.validate(command, execution.getCommandSender());
      commandExecutor.execute(execution);
    } catch (ConformableException exception) {
      exceptionHandler.handle(execution, exception);
    }
    return true;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public @NotNull List<String> tabComplete(
      @NotNull CommandSender sender,
      @NotNull String alias,
      @NotNull String[] args,
      @Nullable Location location
  ) throws IllegalArgumentException {
    CommandExecution execution = executionFactory.create(
        command,
        sender,
        Arrays.stream(args).toList()
    );
    return commandCompleter.completeSorted(execution);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void register(Object @NotNull ... args) {
    Bukkit.getCommandMap().register("commander", this);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public boolean testPermissionSilent(@NotNull CommandSender target) {
    return commandFilterService.isAccessible(
        new BukkitCommandSender(target),
        command
    );
  }
}
