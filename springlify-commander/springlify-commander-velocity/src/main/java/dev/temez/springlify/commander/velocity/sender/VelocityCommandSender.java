package dev.temez.springlify.commander.velocity.sender;

import com.velocitypowered.api.command.CommandSource;
import com.velocitypowered.api.proxy.ConsoleCommandSource;
import com.velocitypowered.api.proxy.Player;
import dev.temez.springlify.commander.commons.sender.Sender;
import java.util.UUID;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.jetbrains.annotations.NotNull;

/**
 * Implementation of {@link Sender} for the Velocity platform.
 *
 * @since 0.5.8.9dev
 */
@Getter
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public final class VelocityCommandSender implements Sender<CommandSource> {

  @NotNull CommandSource platformSender;

  /**
   * {@inheritDoc}
   */
  @Override
  public @NotNull UUID getUuid() throws IllegalArgumentException {
    if (isConsoleSender()) {
      throw new IllegalStateException("Command sender is console!");
    }
    return ((Player) platformSender).getUniqueId();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public boolean isConsoleSender() {
    return platformSender instanceof ConsoleCommandSource;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public boolean hasPermission(@NotNull String permission) {
    return platformSender.hasPermission(permission);
  }
}
