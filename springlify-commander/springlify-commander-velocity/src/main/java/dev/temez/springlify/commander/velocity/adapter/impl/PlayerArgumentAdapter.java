package dev.temez.springlify.commander.velocity.adapter.impl;

import com.velocitypowered.api.proxy.Player;
import com.velocitypowered.api.proxy.ProxyServer;
import dev.temez.springlify.commander.commons.adapter.ArgumentAdapter;
import dev.temez.springlify.commander.commons.exception.ConformableException;
import dev.temez.springlify.commander.commons.sender.Sender;
import java.util.List;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Unmodifiable;
import org.springframework.stereotype.Component;

/**
 * Implementation of {@link ArgumentAdapter} for the BungeeCord platform,
 * adapting arguments to {@link Player}.
 *
 * @since 0.5.8.9dev
 */
@Component
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public final class PlayerArgumentAdapter implements ArgumentAdapter<Player> {

  @NotNull ProxyServer proxyServer;

  /**
   * {@inheritDoc}
   */
  @Override
  public @Unmodifiable @NotNull List<String> complete(@NotNull Sender<?> commandSender) {
    return proxyServer.getAllPlayers()
        .stream()
        .map(Player::getUsername)
        .toList();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public @NotNull Class<Player> getTargetClass() {
    return Player.class;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public @NotNull Player parse(
      @NotNull Sender<?> commandSender,
      @NotNull String rawArgument
  ) throws ConformableException {
    return proxyServer.getPlayer(rawArgument)
        .orElseThrow(() -> new ConformableException("commander.arguments.no-such-a-player-online"));
  }
}
