package dev.temez.springlify.commander.argument.adapter.impl;

import dev.temez.springlify.commander.argument.adapter.ParameterAdapter;
import dev.temez.springlify.commander.command.sender.Sender;
import dev.temez.springlify.commander.exception.argument.ArgumentException;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * A parameter adapter for completing and parsing Bukkit offline player arguments.
 * This class implements the {@link ParameterAdapter} interface for {@link OfflinePlayer} types.
 *
 * @since 0.7.0.0-RC1
 */
@Component
public class OfflinePlayerParameterAdapter implements ParameterAdapter<OfflinePlayer> {

  /**
   * Provides a list of all player names (online and offline) for completion.
   *
   * @param commandSender the command sender
   * @return an unmodifiable list of all player names
   */
  @Override
  public @NotNull List<String> complete(@NotNull Sender<?> commandSender) {
    return Bukkit.getOnlinePlayers()
        .stream()
        .map(OfflinePlayer::getName)
        .toList();
  }

  /**
   * Parses the raw argument into a Bukkit offline player.
   *
   * @param commandSender the command sender
   * @param rawArgument   the raw argument string
   * @return the parsed Bukkit offline player
   * @throws ArgumentException if the player has not played before on the server
   */
  @Override
  public @NotNull OfflinePlayer parse(@NotNull Sender<?> commandSender, @NotNull String rawArgument) throws ArgumentException {
    OfflinePlayer player = Bukkit.getOfflinePlayer(rawArgument);
    if (!player.hasPlayedBefore()) {
      throw new ArgumentException("commander.parameters.player-have-not-played-on-this-server");
    }
    return player;
  }
}
