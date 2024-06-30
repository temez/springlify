package dev.temez.springlify.commander.command.invocation;


import dev.temez.springlify.commander.command.Command;
import dev.temez.springlify.commander.command.sender.BukkitCommandSender;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * A factory class for creating instances of {@link CommandInvocation} specific to the Bukkit platform.
 * This class implements the {@link CommandInvocationFactory} interface.
 *
 * @since 0.7.0.0-RC1
 */
@Component
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class BukkitInvocationFactory implements CommandInvocationFactory {

  /**
   * Creates a new {@link CommandInvocation} instance for the given command and arguments.
   * This method expects the first object in the arguments array to be a {@link CommandSender}
   * and the second object to be a {@link List} of {@link String} representing the command arguments.
   *
   * @param command the command for which the invocation is being created
   * @param objects the arguments for the command invocation, expecting a {@link CommandSender} and a {@link List} of {@link String}
   * @return a new {@link CommandInvocation} instance
   */
  @Override
  @SuppressWarnings("unchecked")
  public @NotNull CommandInvocation create(@NotNull Command command, Object @NotNull ... objects) {
    return new CommandInvocationImpl(
        new BukkitCommandSender((CommandSender) objects[0]),
        command,
        (List<String>) objects[1]
    );
  }
}
