package dev.temez.springlify.commander.bukkit.adapter.impl;

import dev.temez.springlify.commander.commons.adapter.ArgumentAdapter;
import dev.temez.springlify.commander.commons.exception.ConformableException;
import dev.temez.springlify.commander.commons.sender.Sender;
import java.util.List;
import org.apache.commons.lang.StringUtils;
import org.bukkit.GameMode;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Unmodifiable;
import org.springframework.stereotype.Component;

/**
 * Argument adapter for converting strings to {@link GameMode} objects.
 *
 * <p>This adapter provides completion suggestions and parses input strings into the corresponding
 * {@link GameMode}.</p>
 *
 * @since 0.5.8.9dev
 */
@Component
public final class GameModeArgumentAdapter implements ArgumentAdapter<GameMode> {

  /**
   * {@inheritDoc}
   *
   * <p>Provides completion suggestions for game modes.</p>
   *
   * @param commandSender The command sender for which to provide completion suggestions.
   * @return A list of completion suggestions.
   */
  @Override
  public @Unmodifiable @NotNull List<String> complete(@NotNull Sender<?> commandSender) {
    return List.of("0", "1", "2", "3", "creative", "survival", "adventure", "spectator");
  }

  /**
   * {@inheritDoc}
   *
   * <p>Parses the input string into a {@link GameMode}.</p>
   *
   * @param commandSender The command sender associated with the input.
   * @param rawArgument   The raw string argument to be parsed.
   * @return The parsed {@link GameMode}.
   * @throws ConformableException If the input string is not a valid game mode.
   */
  @Override
  @SuppressWarnings("deprecation")
  public @NotNull GameMode parse(
      @NotNull Sender<?> commandSender,
      @NotNull String rawArgument
  ) throws ConformableException {
    if (StringUtils.isNumeric(rawArgument)) {
      int gamemodeCode = Integer.parseInt(rawArgument);
      GameMode gameMode = GameMode.getByValue(gamemodeCode);
      if (gameMode == null) {
        throw new ConformableException("commander.arguments.invalid-gamemode");
      }
      return gameMode;
    }

    try {
      return GameMode.valueOf(rawArgument.toUpperCase());
    } catch (IllegalArgumentException e) {
      throw new ConformableException("commander.arguments.invalid-gamemode");
    }
  }

  /**
   * {@inheritDoc}
   *
   * <p>Gets the target class for this argument adapter.</p>
   *
   * @return The target class, which is {@link GameMode}.
   */
  @Override
  public @NotNull Class<GameMode> getTargetClass() {
    return GameMode.class;
  }
}

