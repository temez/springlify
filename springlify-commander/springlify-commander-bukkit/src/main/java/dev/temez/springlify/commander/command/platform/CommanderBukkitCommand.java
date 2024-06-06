package dev.temez.springlify.commander.command.platform;

import dev.temez.springlify.commander.command.Command;
import dev.temez.springlify.commander.command.execution.BukkitInvocationFactory;
import dev.temez.springlify.commander.command.invocation.CommandInvocation;
import dev.temez.springlify.commander.command.invocation.CommandInvocationFactory;
import dev.temez.springlify.commander.service.CommandService;
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
  CommandService commandService;

  protected CommanderBukkitCommand(
      @NotNull Command command,
      @NotNull CommandService commandService
  ) {
    super(command.getName());
    this.commandService = commandService;
    setAliases(command.getAlias());
    this.command = command;
  }

  @Override
  public boolean execute(@NotNull CommandSender sender, @NotNull String commandLabel,
                         @NotNull String[] args) {
    CommandInvocation execution = executionFactory.create(
        command,
        sender,
        Arrays.stream(args).toList()
    );
    commandService.execute(execution);
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
    return commandService.complete(execution);
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
