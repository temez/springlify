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

@Component
public final class GameModeArgumentAdapter implements ArgumentAdapter<GameMode> {

  @Override
  public @Unmodifiable @NotNull List<String> complete(@NotNull Sender<?> commandSender) {
    return List.of("0", "1", "2", "3", "creative", "survival", "adventure", "spectator");
  }

  @Override
  public @NotNull Class<GameMode> getTargetClass() {
    return GameMode.class;
  }

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
    } catch (Exception e) {
      throw new ConformableException("commander.arguments.invalid-gamemode");
    }
  }
}
