package dev.temez.springlify.commander.command.platform;

import dev.temez.springlify.commander.command.Command;
import dev.temez.springlify.commander.command.completer.CommandCompleter;
import dev.temez.springlify.commander.command.execution.BukkitInvocationFactory;
import dev.temez.springlify.commander.command.invocation.CommandInvocation;
import dev.temez.springlify.commander.command.invocation.CommandInvocationFactory;
import dev.temez.springlify.commander.command.preprocessor.ExecutionPreprocessor;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Arrays;
import java.util.List;


@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class CommanderBukkitCommand extends org.bukkit.command.Command implements PlatformCommand {

  @NotNull
  CommandInvocationFactory executionFactory = new BukkitInvocationFactory();

  @NotNull
  Command command;

  @NotNull
  CommandCompleter commandCompleter;

  @NotNull
  ExecutionPreprocessor executionPreprocessor;

  protected CommanderBukkitCommand(
      @NotNull Command command,
      @NotNull CommandCompleter commandCompleter,
      @NotNull ExecutionPreprocessor executionPreprocessor
  ) {
    super(command.getName());
    this.executionPreprocessor = executionPreprocessor;
    setAliases(command.getAlias());
    this.command = command;
    this.commandCompleter = commandCompleter;
  }

  @Override
  public boolean execute(@NotNull CommandSender sender, @NotNull String commandLabel,
                         @NotNull String[] args) {
    return true;
  }

  @Override
  public @NotNull List<String> tabComplete(
      @NotNull CommandSender sender,
      @NotNull String alias,
      @NotNull String[] args,
      @Nullable Location location
  ) throws IllegalArgumentException {
    CommandInvocation execution = executionFactory.create(
        command,
        sender,
        Arrays.stream(args).toList()
    );
    executionPreprocessor.process(execution);
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
    return true;
  }
}
