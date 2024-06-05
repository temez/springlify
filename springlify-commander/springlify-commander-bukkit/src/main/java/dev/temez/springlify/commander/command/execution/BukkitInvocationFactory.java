package dev.temez.springlify.commander.command.execution;


import dev.temez.springlify.commander.command.Command;
import dev.temez.springlify.commander.command.invocation.CommandInvocation;
import dev.temez.springlify.commander.command.invocation.CommandInvocationFactory;
import dev.temez.springlify.commander.command.invocation.CommandInvocationImpl;
import dev.temez.springlify.commander.command.sender.BukkitCommandSender;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Component;

import java.util.List;


@Component
public class BukkitInvocationFactory implements CommandInvocationFactory {

  @Override
  @SuppressWarnings("unchecked")
  public @NotNull CommandInvocation create(
      @NotNull Command command,
      Object @NotNull ... objects
  ) {
    return new CommandInvocationImpl(
        new BukkitCommandSender((CommandSender) objects[0]),
        command,
        ((List<String>) objects[1])
    );
  }
}
