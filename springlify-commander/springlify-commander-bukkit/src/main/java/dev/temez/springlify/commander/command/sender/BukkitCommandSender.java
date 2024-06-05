package dev.temez.springlify.commander.command.sender;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.UUID;

@Getter
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public final class BukkitCommandSender implements Sender<CommandSender> {

  @NotNull
  CommandSender platformSender;

  @Override
  public @NotNull UUID getUuid() throws IllegalArgumentException {
    if (isConsoleSender()) {
      throw new IllegalStateException("Command sender is console!");
    }
    return ((Player) platformSender).getUniqueId();
  }

  @Override
  public boolean isConsoleSender() {
    return !(platformSender instanceof Player);
  }

  @Override
  public boolean hasPermission(@NotNull String permission) {
    return platformSender.hasPermission(permission);
  }
}
