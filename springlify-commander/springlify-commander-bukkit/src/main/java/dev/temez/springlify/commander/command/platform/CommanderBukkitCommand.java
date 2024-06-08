package dev.temez.springlify.commander.command.platform;

import dev.temez.springlify.commander.command.Command;
import dev.temez.springlify.commander.command.filter.CommandFilterService;
import dev.temez.springlify.commander.command.invocation.BukkitInvocationFactory;
import dev.temez.springlify.commander.command.invocation.CommandInvocation;
import dev.temez.springlify.commander.command.invocation.CommandInvocationFactory;
import dev.temez.springlify.commander.command.sender.BukkitCommandSender;
import dev.temez.springlify.commander.service.CommandService;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.bukkit.Location;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Arrays;
import java.util.List;


@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public final class CommanderBukkitCommand extends org.bukkit.command.Command {

  @NotNull
  CommandInvocationFactory invocationFactory = new BukkitInvocationFactory();

  @NotNull
  Command command;

  @NotNull
  CommandService commandService;

  @NotNull
  CommandFilterService commandFilterService;

  public CommanderBukkitCommand(
      @NotNull Command command,
      @NotNull CommandService commandService,
      @NotNull CommandFilterService commandFilterService
  ) {
    super(command.getName());
    this.commandService = commandService;
    this.commandFilterService = commandFilterService;
    setAliases(command.getAlias());
    this.command = command;
  }

  @Override
  public boolean execute(@NotNull CommandSender sender, @NotNull String commandLabel,
                         @NotNull String[] args) {
    CommandInvocation execution = invocationFactory.create(
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
    CommandInvocation execution = invocationFactory.create(
        command,
        sender,
        Arrays.stream(args).toList()
    );
    return commandService.complete(execution);
  }

  /**
   * {@inheritDoc}
   *
   * @param target
   * @return
   */
  @Override
  public boolean testPermission(@NotNull CommandSender target) {
    return super.testPermission(target);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public boolean testPermissionSilent(@NotNull CommandSender target) {
    return commandFilterService.isAccessible(new BukkitCommandSender(target), command);
  }
}
