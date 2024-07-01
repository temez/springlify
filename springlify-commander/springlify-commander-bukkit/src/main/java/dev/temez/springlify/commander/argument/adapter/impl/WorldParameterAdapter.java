package dev.temez.springlify.commander.argument.adapter.impl;

import dev.temez.springlify.commander.argument.adapter.ParameterAdapter;
import dev.temez.springlify.commander.command.sender.Sender;
import dev.temez.springlify.commander.exception.argument.ArgumentException;
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

/**
 * A parameter adapter for completing and parsing Bukkit world arguments.
 * This class implements the {@link ParameterAdapter} interface for {@link World} types.
 *
 * @since 0.7.0.0-RC1
 */
@Component
public class WorldParameterAdapter implements ParameterAdapter<World> {

  /**
   * Provides a list of world names for completion.
   *
   * @param commandSender the command sender
   * @return an unmodifiable list of world names
   */
  @Override
  public @NotNull List<String> complete(@NotNull Sender<?> commandSender) {
    return Bukkit.getWorlds()
        .stream()
        .map(World::getName)
        .toList();
  }

  /**
   * Parses the raw argument into a Bukkit world.
   *
   * @param commandSender the command sender
   * @param rawArgument   the raw argument string
   * @return the parsed Bukkit world
   * @throws ArgumentException if the world does not exist
   */
  @Override
  public @NotNull World parse(@NotNull Sender<?> commandSender, @NotNull String rawArgument) throws ArgumentException {
    return Optional.ofNullable(Bukkit.getWorld(rawArgument))
        .orElseThrow(() -> new ArgumentException("commander.parameters.commander-world-does-not-exist"));
  }
}
