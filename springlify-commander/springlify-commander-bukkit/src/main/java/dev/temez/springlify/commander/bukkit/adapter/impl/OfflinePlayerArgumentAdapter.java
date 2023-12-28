package dev.temez.springlify.commander.bukkit.adapter.impl;

import dev.temez.springlify.commander.commons.adapter.ArgumentAdapter;
import dev.temez.springlify.commander.commons.exception.ConformableException;
import dev.temez.springlify.commander.commons.sender.Sender;
import java.util.List;
import java.util.stream.Collectors;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.HumanEntity;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Unmodifiable;
import org.springframework.stereotype.Component;

/**
 * Argument adapter for converting strings to {@link OfflinePlayer} objects.
 *
 * <p>This adapter provides completion suggestions and parses input strings into
 * the corresponding {@link OfflinePlayer}.</p>
 *
 * @since 0.5.8.9dev
 */
@Component
public final class OfflinePlayerArgumentAdapter implements ArgumentAdapter<OfflinePlayer> {

  /**
   * {@inheritDoc}
   *
   * <p>Provides completion suggestions for offline players based on online players' names.</p>
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
   * <p>Parses the input string into an {@link OfflinePlayer}.</p>
   *
   * @param commandSender The command sender associated with the input.
   * @param rawArgument   The raw string argument to be parsed.
   * @return The parsed {@link OfflinePlayer}.
   * @throws ConformableException If the input string does not correspond to an existing player.
   */
  @Override
  public @NotNull OfflinePlayer parse(
      @NotNull Sender<?> commandSender,
      @NotNull String rawArgument
  ) throws ConformableException {
    OfflinePlayer player = Bukkit.getOfflinePlayer(rawArgument);
    if (!player.hasPlayedBefore()) {
      throw new ConformableException("commander.arguments.player-have-not-player-on-this-server");
    }
    return player;
  }

  /**
   * {@inheritDoc}
   *
   * <p>Gets the target class for this argument adapter.</p>
   *
   * @return The target class, which is {@link OfflinePlayer}.
   */
  @Override
  public @NotNull Class<OfflinePlayer> getTargetClass() {
    return OfflinePlayer.class;
  }
}
