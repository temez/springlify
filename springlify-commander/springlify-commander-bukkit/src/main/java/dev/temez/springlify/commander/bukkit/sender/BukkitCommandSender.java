package dev.temez.springlify.commander.bukkit.sender;

import dev.temez.springlify.commander.commons.sender.Sender;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

/**
 * Implementation of {@link Sender} for the Bukkit platform.
 *
 * <p>This class represents a command sender wrapping a {@link CommandSender}.</p>
 *
 * @since 0.5.8.9dev
 */
@Getter
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public final class BukkitCommandSender implements Sender<CommandSender> {

  /**
   * The underlying Bukkit {@link CommandSender}.
   */
  @NotNull CommandSender platformSender;

  /**
   * {@inheritDoc}
   */
  @Override
  public boolean isConsoleSender() {
    return !(platformSender instanceof Player);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public boolean hasPermission(@NotNull String permission) {
    return platformSender.hasPermission(permission);
  }
}
