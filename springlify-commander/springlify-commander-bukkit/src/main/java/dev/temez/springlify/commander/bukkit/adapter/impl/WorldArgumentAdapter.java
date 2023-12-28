package dev.temez.springlify.commander.bukkit.adapter.impl;

import dev.temez.springlify.commander.commons.adapter.ArgumentAdapter;
import dev.temez.springlify.commander.commons.exception.ConformableException;
import dev.temez.springlify.commander.commons.sender.Sender;
import java.util.List;
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Component;

@Component
public class WorldArgumentAdapter implements ArgumentAdapter<World> {

  @Override
  @SuppressWarnings("Convert2MethodRef")
  public @NotNull List<String> complete(@NotNull Sender<?> commandSender) {
    return Bukkit.getWorlds()
        .stream().map(w -> w.getName())
        .toList();
  }


  @Override
  public @NotNull Class<World> getTargetClass() {
    return World.class;
  }

  @Override
  public @NotNull World parse(
      @NotNull Sender<?> commandSender,
      @NotNull String rawArgument
  ) throws ConformableException {
    World world = Bukkit.getWorld(rawArgument);
    if (world == null) {
      throw new ConformableException("commander-world-does-not-exists");
    }
    return world;
  }
}
