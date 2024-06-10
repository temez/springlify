package dev.temez.springlify.commander.argument.adapter.impl;

import dev.temez.springlify.commander.argument.adapter.ParameterAdapter;
import dev.temez.springlify.commander.command.sender.Sender;
import dev.temez.springlify.commander.exception.argument.ArgumentException;
import org.apache.commons.lang.StringUtils;
import org.bukkit.GameMode;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * A parameter adapter for completing and parsing Bukkit game mode arguments.
 * This class implements the {@link ParameterAdapter} interface for {@link GameMode} types.
 *
 * @since 0.7.0.0-RC1
 */
@Component
public final class GameModeParameterAdapter implements ParameterAdapter<GameMode> {

  /**
   * Provides a list of game mode names and their respective numeric values for completion.
   *
   * @param commandSender the command sender
   * @return an unmodifiable list of game mode names and their respective numeric values
   */
  @Override
  public @NotNull List<String> complete(@NotNull Sender<?> commandSender) {
    return List.of("0", "1", "2", "3", "creative", "survival", "adventure", "spectator");
  }

  /**
   * Parses the raw argument into a Bukkit game mode.
   *
   * @param commandSender the command sender
   * @param rawArgument   the raw argument string
   * @return the parsed Bukkit game mode
   * @throws ArgumentException if the argument does not represent a valid game mode
   */
  @Override
  public @NotNull GameMode parse(@NotNull Sender<?> commandSender, @NotNull String rawArgument) throws ArgumentException {
    if (StringUtils.isNumeric(rawArgument)) {
      int gamemodeCode = Integer.parseInt(rawArgument);
      GameMode gameMode = GameMode.getByValue(gamemodeCode);
      if (gameMode == null) {
        throw new ArgumentException("commander.parameters.invalid-gamemode");
      }
      return gameMode;
    }

    try {
      return GameMode.valueOf(rawArgument.toUpperCase());
    } catch (IllegalArgumentException e) {
      throw new ArgumentException("commander.parameters.invalid-gamemode");
    }
  }
}
