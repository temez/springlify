package dev.temez.springlify.commander.bukkit.adapter.impl;

import dev.temez.springlify.commander.commons.adapter.ArgumentAdapter;
import dev.temez.springlify.commander.commons.exception.ConformableException;
import dev.temez.springlify.commander.commons.sender.Sender;
import java.util.List;
import java.util.stream.Collectors;
import org.bukkit.Bukkit;
import org.bukkit.entity.HumanEntity;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Unmodifiable;
import org.springframework.stereotype.Component;

/**
 * Argument adapter for converting strings to {@link Player} objects.
 *
 * <p>This adapter provides completion suggestions and parses input strings into
 * the corresponding {@link Player}.</p>
 *
 * @since 0.5.8.9dev
 */
@Component
public final class PlayerArgumentAdapter implements ArgumentAdapter<Player> {

  /**
   * {@inheritDoc}
   *
   * <p>Provides completion suggestions for online players' names.</p>
   *
   * @param commandSender The command sender for which to provide completion suggestions.
   * @return A list of completion suggestions.
   */
  @Override
  public @Unmodifiable @NotNull List<String> complete(@NotNull Sender<?> commandSender) {
    return Bukkit.getOnlinePlayers()
        .stream()
        .map(HumanEntity::getName)
        .collect(Collectors.toList());
  }

  /**
   * {@inheritDoc}
   *
   * <p>Parses the input string into a {@link Player}.</p>
   *
   * @param commandSender The command sender associated with the input.
   * @param rawArgument   The raw string argument to be parsed.
   * @return The parsed {@link Player}.
   * @throws ConformableException If the player specified by the input string is offline.
   */
  @Override
  public @NotNull Player parse(
      @NotNull Sender<?> commandSender,
      @NotNull String rawArgument
  ) throws ConformableException {
    Player player = Bukkit.getPlayer(rawArgument);
    if (player == null) {
      throw new ConformableException("commander.arguments.player-is-offline");
    }
    return player;
  }

  /**
   * {@inheritDoc}
   *
   * <p>Gets the target class for this argument adapter.</p>
   *
   * @return The target class, which is {@link Player}.
   */
  @Override
  public @NotNull Class<Player> getTargetClass() {
    return Player.class;
  }
}
