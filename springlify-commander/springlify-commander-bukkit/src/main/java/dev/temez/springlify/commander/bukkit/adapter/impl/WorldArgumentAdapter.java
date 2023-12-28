package dev.temez.springlify.commander.bukkit.adapter.impl;

import dev.temez.springlify.commander.commons.adapter.ArgumentAdapter;
import dev.temez.springlify.commander.commons.exception.ConformableException;
import dev.temez.springlify.commander.commons.sender.Sender;
import java.util.List;
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Component;

/**
 * Argument adapter for converting strings to {@link World} objects.
 *
 * <p>This adapter provides completion suggestions and parses input strings into the corresponding
 * {@link World}.</p>
 *
 * @since 0.5.8.9dev
 */
@Component
public class WorldArgumentAdapter implements ArgumentAdapter<World> {

  /**
   * {@inheritDoc}
   *
   * <p>Provides completion suggestions for world names.</p>
   *
   * @param commandSender The command sender for which to provide completion suggestions.
   * @return A list of completion suggestions.
   */
  @Override
  public @NotNull List<String> complete(@NotNull Sender<?> commandSender) {
    return Bukkit.getWorlds()
        .stream()
        .map(World::getName)
        .toList();
  }

  /**
   * {@inheritDoc}
   *
   * <p>Parses the input string into a {@link World}.</p>
   *
   * @param commandSender The command sender associated with the input.
   * @param rawArgument   The raw string argument to be parsed.
   * @return The parsed {@link World}.
   * @throws ConformableException If the world specified by the input string does not exist.
   */
  @Override
  public @NotNull World parse(
      @NotNull Sender<?> commandSender,
      @NotNull String rawArgument
  ) throws ConformableException {
    World world = Bukkit.getWorld(rawArgument);
    if (world == null) {
      throw new ConformableException("commander-world-does-not-exist");
    }
    return world;
  }

  /**
   * {@inheritDoc}
   *
   * <p>Gets the target class for this argument adapter.</p>
   *
   * @return The target class, which is {@link World}.
   */
  @Override
  public @NotNull Class<World> getTargetClass() {
    return World.class;
  }
}
