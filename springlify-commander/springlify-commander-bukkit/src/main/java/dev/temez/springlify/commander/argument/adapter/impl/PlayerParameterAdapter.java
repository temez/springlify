package dev.temez.springlify.commander.argument.adapter.impl;


import dev.temez.springlify.commander.argument.adapter.ParameterAdapter;
import dev.temez.springlify.commander.command.sender.Sender;
import dev.temez.springlify.commander.exception.argument.ArgumentException;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

/**
 * A parameter adapter for completing and parsing Bukkit player arguments.
 * This class implements the {@link ParameterAdapter} interface for {@link Player} types.
 *
 * @since 0.7.0.0-RC1
 */
@Component
public class PlayerParameterAdapter implements ParameterAdapter<Player> {

  /**
   * Provides a list of online player names for completion.
   * If the command sender is a player, filters out players who are not visible to the sender.
   *
   * @param commandSender the command sender
   * @return an unmodifiable list of online player names
   */
  @Override
  public @NotNull List<String> complete(@NotNull Sender<?> commandSender) {
    return Bukkit.getOnlinePlayers()
        .stream()
        .map(Player::getName)
        .toList();
  }

  /**
   * Parses the raw argument into a Bukkit player.
   *
   * @param commandSender the command sender
   * @param rawArgument   the raw argument string
   * @return the parsed Bukkit player
   * @throws ArgumentException if the player is offline
   */
  @Override
  public @NotNull Player parse(@NotNull Sender<?> commandSender, @NotNull String rawArgument) throws ArgumentException {
    return Optional.ofNullable(Bukkit.getPlayer(rawArgument))
        .orElseThrow(() -> new ArgumentException("commander.parameters.player-is-offline"));
  }
}
