package dev.temez.springlify.commander.command.sender;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.UUID;

/**
 * A wrapper class for Bukkit's {@link CommandSender} that implements the {@link Sender} interface.
 * This class provides methods to interact with the command sender, including checking for permissions
 * and retrieving the sender's UUID.
 *
 * @since 0.7.0.0-RC1
 */
@Getter
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class BukkitCommandSender implements Sender<CommandSender> {

  /**
   * The underlying platform-specific command sender.
   */
  @NotNull
  CommandSender platformSender;

  /**
   * Returns the UUID of the command sender if they are a player.
   *
   * @return the UUID of the player
   * @throws UnsupportedOperationException if the command sender is the console
   */
  @Override
  public @NotNull UUID getUuid() throws UnsupportedOperationException {
    if (isConsoleSender()) {
      throw new UnsupportedOperationException("Command sender is console!");
    }
    return ((Player) platformSender).getUniqueId();
  }

  /**
   * Checks if the command sender is the console.
   *
   * @return {@code true} if the sender is the console, {@code false} otherwise
   */
  @Override
  public boolean isConsoleSender() {
    return !(platformSender instanceof Player);
  }

  /**
   * Checks if the command sender has the specified permission.
   *
   * @param permission the permission to check
   * @return {@code true} if the sender has the permission, {@code false} otherwise
   */
  @Override
  public boolean hasPermission(@NotNull String permission) {
    return platformSender.hasPermission(permission);
  }
}
