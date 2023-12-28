package dev.temez.springlify.commander.bukkit.adapter.impl;

import dev.temez.springlify.commander.commons.adapter.ArgumentAdapter;
import dev.temez.springlify.commander.commons.exception.ConformableException;
import dev.temez.springlify.commander.commons.sender.Sender;
import java.util.List;
import org.bukkit.Bukkit;
import org.bukkit.entity.HumanEntity;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Unmodifiable;
import org.springframework.stereotype.Component;

@Component
public final class PlayerArgumentAdapter implements ArgumentAdapter<Player> {

  @Override
  public @Unmodifiable @NotNull List<String> complete(@NotNull Sender<?> commandSender) {
    return Bukkit.getOnlinePlayers()
        .stream()
        .map(HumanEntity::getName)
        .toList();
  }

  @Override
  public @NotNull Class<Player> getTargetClass() {
    return Player.class;
  }

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
}
