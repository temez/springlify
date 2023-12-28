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

@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class CommanderBukkitCommand extends Command implements PlatformCommand {

  @NotNull CommandExecutionFactory executionFactory = new BukkitExecutionFactory();

  @NotNull RegisteredCommand command;

  @NotNull CommandFilterService commandFilterService;

  @NotNull CommandCompleter commandCompleter;

  @NotNull CommandExecutor commandExecutor;

  @NotNull CommanderExceptionHandler exceptionHandler;

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

  @Override
  public boolean execute(@NotNull CommandSender sender, @NotNull String commandLabel,
                         @NotNull String[] args) {
    CommandExecution execution = executionFactory.createExecution(
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

  @Override
  public @NotNull List<String> tabComplete(
      @NotNull CommandSender sender,
      @NotNull String alias,
      @NotNull String[] args,
      @Nullable Location location
  ) throws IllegalArgumentException {
    CommandExecution execution = executionFactory.createExecution(
        command,
        sender,
        Arrays.stream(args).toList()
    );
    return commandCompleter.completeSorted(execution);
  }

  @Override
  public void register(Object @NotNull ... args) {
    Bukkit.getCommandMap().register("commander", this);
  }

  @Override
  public boolean testPermissionSilent(@NotNull CommandSender target) {
    return commandFilterService.isAccessible(
        new BukkitCommandSender(target),
        command
    );
  }
}
